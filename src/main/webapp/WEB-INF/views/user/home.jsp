<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>Trang chủ</title>
  <meta name="heading" content="Video theo danh mục">
  <meta name="subtitle" content="Khám phá và chia sẻ những video yêu thích của bạn">
</head>
<body>
  <ul class="breadcrumb">
    <li><a href="${ctx}/home">Trang chủ</a></li>
    <li class="active">Video theo danh mục</li>
  </ul>

  <div id="san-pham" class="row margin-bottom-40">
    <div class="col-md-12">
      <c:forEach var="b" items="${blocks}">
        <c:set var="cid" value="${b.category.categoryId}"/>
        <section class="category-block" id="cat-${cid}">
          <h2 class="category-title"><c:out value="${b.category.categoryname}"/> (${b.total})</h2>
          <c:if test="${empty b.videos}">
            <p class="text-muted">Chưa có video nào.</p>
          </c:if>

          <div class="row product-list">
            <c:forEach var="v" items="${b.videos}">
              <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="product-item video-item">
                  <div class="pi-img-wrapper">
                    <a href="${ctx}/video?id=${v.videoId}">
                      <img class="img-responsive" src="${ctx}/image?name=${empty v.poster ? 'no-image.svg' : v.poster}" alt="poster">
                    </a>
                    <div>
                      <a href="${ctx}/video?id=${v.videoId}" class="btn btn-default"><i class="fa fa-play"></i> Xem</a>
                    </div>
                  </div>
                  <h3 class="video-title">Tiêu đề: <a href="${ctx}/video?id=${v.videoId}"><c:out value="${v.title}"/></a></h3>
                  <ul class="list-unstyled video-meta">
                    <li>Mã video: <c:out value="${v.videoId}"/></li>
                    <li>Category name: <c:out value="${v.categoryName}"/></li>
                    <li>View: ${v.views}</li>
                    <li>Share(${v.shares})</li>
                    <li>Like(${v.likes})</li>
                    <li>Còn lại: ${v.stock}</li>
                  </ul>
                  <div class="pi-price">${v.priceText}</div>
                  <c:choose>
                    <c:when test="${v.inStock}">
                      <form class="add-cart-form" method="post" action="${ctx}/cart">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="id" value="<c:out value='${v.videoId}'/>">
                        <input type="hidden" name="quantity" value="1">
                        <button type="submit" class="btn btn-primary"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ</button>
                      </form>
                    </c:when>
                    <c:otherwise>
                      <span class="label label-default">Hết hàng</span>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
            </c:forEach>
          </div>

          <div class="row">
            <div class="col-md-12 text-center">
              <ul class="pagination">
                <li class="${b.page > 1 ? '' : 'disabled'}"><a href="${ctx}/home?${b.otherParams}p${cid}=${b.page > 1 ? b.page - 1 : 1}#cat-${cid}">&laquo;</a></li>
                <c:forEach begin="1" end="${b.totalPages}" var="i">
                  <li class="${i == b.page ? 'active' : ''}"><a href="${ctx}/home?${b.otherParams}p${cid}=${i}#cat-${cid}">${i}</a></li>
                </c:forEach>
                <li class="${b.page < b.totalPages ? '' : 'disabled'}"><a href="${ctx}/home?${b.otherParams}p${cid}=${b.page < b.totalPages ? b.page + 1 : b.totalPages}#cat-${cid}">&raquo;</a></li>
              </ul>
            </div>
          </div>
        </section>
      </c:forEach>
    </div>
  </div>
</body>
</html>
