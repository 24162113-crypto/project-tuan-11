package vn.hcmute.service;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import vn.hcmute.dao.IVideoDAO_24162113;
import vn.hcmute.dao.VideoDAOImpl_24162113;
import vn.hcmute.dto.Cart_24162113;
import vn.hcmute.dto.CartItem_24162113;
import vn.hcmute.entity.Video_24162113;

public class CartServiceImpl_24162113 implements ICartService_24162113 {
    private final IVideoDAO_24162113 videoDAO = new VideoDAOImpl_24162113();

    @Override
    public Cart_24162113 getCart(HttpSession session) {
        Object value = session.getAttribute("cart");
        if (value instanceof Cart_24162113) {
            return (Cart_24162113) value;
        }
        Cart_24162113 cart = new Cart_24162113();
        session.setAttribute("cart", cart);
        return cart;
    }

    private static int stockOf(Video_24162113 video) {
        return video.getStock() == null ? 0 : Math.max(0, video.getStock());
    }

    private static long priceOf(Video_24162113 video) {
        return video.getPrice() == null ? 0 : Math.max(0, video.getPrice());
    }

    private static boolean sellable(Video_24162113 video) {
        return video != null && Boolean.TRUE.equals(video.getActive()) && stockOf(video) > 0;
    }

    private static String limitMessage(int stock) {
        if (stock < CartItem_24162113.MAX_PER_ITEM) {
            return "Chỉ còn " + stock + " sản phẩm trong kho, số lượng đã được điều chỉnh";
        }
        return "Mỗi sản phẩm chỉ được mua tối đa " + CartItem_24162113.MAX_PER_ITEM + ", số lượng đã được điều chỉnh";
    }

    private Video_24162113 load(String videoId) {
        return videoId == null || videoId.isEmpty() ? null : videoDAO.findById(videoId);
    }

    private void apply(Cart_24162113 cart, CartItem_24162113 existing, Video_24162113 video, int quantity) {
        if (existing == null) {
            cart.put(new CartItem_24162113(video.getVideoId(), video.getTitle(), video.getPoster(), priceOf(video), stockOf(video), quantity));
            return;
        }
        existing.setTitle(video.getTitle());
        existing.setPoster(video.getPoster());
        existing.setPrice(priceOf(video));
        existing.setStock(stockOf(video));
        existing.setQuantity(quantity);
    }

    @Override
    public String add(Cart_24162113 cart, String videoId, int quantity) {
        Video_24162113 video = load(videoId);
        if (video == null || !Boolean.TRUE.equals(video.getActive())) {
            return "Sản phẩm không tồn tại hoặc đã ngừng bán";
        }
        if (stockOf(video) <= 0) {
            return "Sản phẩm đã hết hàng";
        }
        int limit = Math.min(stockOf(video), CartItem_24162113.MAX_PER_ITEM);
        CartItem_24162113 existing = cart.get(video.getVideoId());
        long target = (long) (existing == null ? 0 : existing.getQuantity()) + Math.max(1, Math.min(quantity, 1000));
        String message = null;
        if (target > limit) {
            target = limit;
            message = limitMessage(stockOf(video));
        }
        apply(cart, existing, video, (int) target);
        return message;
    }

    @Override
    public String update(Cart_24162113 cart, String videoId, int quantity) {
        CartItem_24162113 existing = cart.get(videoId);
        if (existing == null) {
            return null;
        }
        if (quantity <= 0) {
            cart.remove(videoId);
            return null;
        }
        Video_24162113 video = load(videoId);
        if (!sellable(video)) {
            cart.remove(videoId);
            return "Sản phẩm \"" + existing.getTitle() + "\" đã hết hàng hoặc ngừng bán, đã xóa khỏi giỏ";
        }
        int limit = Math.min(stockOf(video), CartItem_24162113.MAX_PER_ITEM);
        int target = quantity;
        String message = null;
        if (target > limit) {
            target = limit;
            message = limitMessage(stockOf(video));
        }
        apply(cart, existing, video, target);
        return message;
    }

    @Override
    public void remove(Cart_24162113 cart, String videoId) {
        cart.remove(videoId);
    }

    @Override
    public void clear(Cart_24162113 cart) {
        cart.clear();
    }

    @Override
    public List<String> refresh(Cart_24162113 cart) {
        List<String> notes = new ArrayList<>();
        for (CartItem_24162113 item : cart.getItems()) {
            Video_24162113 video = load(item.getVideoId());
            if (!sellable(video)) {
                cart.remove(item.getVideoId());
                notes.add("Sản phẩm \"" + item.getTitle() + "\" đã hết hàng hoặc ngừng bán, đã xóa khỏi giỏ");
                continue;
            }
            long oldPrice = item.getPrice();
            int limit = Math.min(stockOf(video), CartItem_24162113.MAX_PER_ITEM);
            int quantity = item.getQuantity();
            if (quantity > limit) {
                quantity = limit;
                notes.add("Sản phẩm \"" + video.getTitle() + "\": " + limitMessage(stockOf(video)));
            }
            apply(cart, item, video, quantity);
            if (oldPrice != priceOf(video)) {
                notes.add("Giá của \"" + video.getTitle() + "\" đã thay đổi");
            }
        }
        return notes;
    }
}
