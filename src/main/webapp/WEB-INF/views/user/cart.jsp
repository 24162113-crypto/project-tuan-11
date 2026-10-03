<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>Giỏ hàng</title>
  <meta name="heading" content="Giỏ hàng của bạn">
  <meta name="subtitle" content="Thêm, sửa số lượng hoặc xóa sản phẩm trước khi thanh toán">
</head>
<body>
  <ul class="breadcrumb">
    <li><a href="${ctx}/home">Trang chủ</a></li>
    <li class="active">Giỏ hàng</li>
  </ul>

  <c:if test="${not empty flashOk}">
    <div class="alert alert-success"><c:out value="${flashOk}"/></div>
  </c:if>
  <c:if test="${not empty flashError}">
    <div class="alert alert-danger"><c:out value="${flashError}"/></div>
  </c:if>
  <c:forEach var="n" items="${notes}">
    <div class="alert alert-warning"><c:out value="${n}"/></div>
  </c:forEach>

  <div class="row margin-bottom-40">
    <div class="col-md-12">
      <c:choose>
        <c:when test="${empty cart.items}">
          <div class="cart-empty">
            <p><i class="fa fa-shopping-cart"></i></p>
            <p>Giỏ hàng của bạn đang trống.</p>
            <a class="btn btn-primary" href="${ctx}/home">Tiếp tục mua sắm</a>
          </div>
        </c:when>
        <c:otherwise>
          <div class="table-responsive">
            <table class="table table-bordered cart-table">
              <thead>
                <tr>
                  <th>Sản phẩm</th>
                  <th class="text-right">Đơn giá</th>
                  <th class="text-center">Số lượng</th>
                  <th class="text-right">Thành tiền</th>
                  <th class="text-center">Xóa</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="it" items="${cart.items}">
                  <tr>
                    <td>
                      <img class="thumb" src="${ctx}/image?name=${empty it.poster ? 'no-image.svg' : it.poster}" alt="poster">
                      <a class="cart-title" href="${ctx}/video?id=${it.videoId}"><c:out value="${it.title}"/></a>
                      <div class="text-muted small">Mã: <c:out value="${it.videoId}"/> &middot; Còn ${it.stock} &middot; Tối đa ${it.limit}/sản phẩm</div>
                    </td>
                    <td class="text-right">${it.priceText}</td>
                    <td class="text-center">
                      <form class="cart-qty-form" method="post" action="${ctx}/cart">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="<c:out value='${it.videoId}'/>">
                        <input type="number" class="form-control input-sm" name="quantity" value="${it.quantity}" min="0" max="${it.limit}">
                        <button type="submit" class="btn btn-default btn-sm">Cập nhật</button>
                      </form>
                    </td>
                    <td class="text-right"><strong>${it.subtotalText}</strong></td>
                    <td class="text-center">
                      <form method="post" action="${ctx}/cart">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="id" value="<c:out value='${it.videoId}'/>">
                        <button type="submit" class="btn btn-danger btn-sm" title="Xóa"><i class="fa fa-trash-o"></i></button>
                      </form>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
              <tfoot>
                <tr>
                  <td colspan="3" class="text-right">Tổng số lượng: <strong>${cart.totalQuantity}</strong> &nbsp; Tổng tiền:</td>
                  <td class="text-right cart-total">${cart.totalAmountText}</td>
                  <td></td>
                </tr>
              </tfoot>
            </table>
          </div>

          <div class="cart-actions">
            <a class="btn btn-default" href="${ctx}/home">&laquo; Tiếp tục mua sắm</a>
            <form class="inline-form" method="post" action="${ctx}/cart" onsubmit="return confirm('Xóa toàn bộ giỏ hàng?');">
              <input type="hidden" name="action" value="clear">
              <button type="submit" class="btn btn-default">Xóa giỏ hàng</button>
            </form>
            <a class="btn btn-primary pull-right" href="${ctx}/checkout">Thanh toán COD <i class="fa fa-arrow-right"></i></a>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</body>
</html>
