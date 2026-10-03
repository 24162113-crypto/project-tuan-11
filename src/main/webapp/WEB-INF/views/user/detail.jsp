<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title><c:out value="${video.title}"/></title>
  <meta name="heading" content="Chi tiết video">
  <meta name="subtitle" content="<c:out value='${video.title}'/>">
</head>
<body>
  <ul class="breadcrumb">
    <li><a href="${ctx}/home">Trang chủ</a></li>
    <li><a href="${ctx}/home#san-pham">Video</a></li>
    <li class="active"><c:out value="${video.title}"/></li>
  </ul>

  <div class="row margin-bottom-40">
    <div class="col-md-12">
      <div class="product-page video-detail">
        <div class="row">
          <div class="col-md-6 col-sm-6">
            <div class="product-main-image">
              <img class="img-responsive" src="${ctx}/image?name=${empty video.poster ? 'no-image.svg' : video.poster}" alt="poster">
            </div>
          </div>
          <div class="col-md-6 col-sm-6">
            <h1>Tiêu đề: <c:out value="${video.title}"/></h1>
            <ul class="list-unstyled video-meta">
              <li>Mã video: <c:out value="${video.videoId}"/></li>
              <li>Category name: <c:out value="${video.categoryName}"/></li>
              <li>View: ${video.views}</li>
              <li>Share(${video.shares})</li>
              <li>Like(${video.likes})</li>
              <li>Còn lại: ${video.stock}</li>
            </ul>
            <div class="pi-price detail-price">${video.priceText}</div>
            <div class="product-page-cart">
              <c:choose>
                <c:when test="${video.inStock}">
                  <form class="add-cart-form" method="post" action="${ctx}/cart">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="id" value="<c:out value='${video.videoId}'/>">
                    <input type="number" class="form-control input-sm qty-input" name="quantity" value="1" min="1" max="${video.maxQuantity}">
                    <button type="submit" class="btn btn-primary"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ</button>
                  </form>
                </c:when>
                <c:otherwise>
                  <span class="label label-default">Hết hàng</span>
                </c:otherwise>
              </c:choose>
              <a class="btn btn-default" href="${ctx}/home">&laquo; Quay lại trang chủ</a>
            </div>
          </div>
        </div>

        <div class="product-page-content">
          <ul id="myTab" class="nav nav-tabs">
            <li class="active"><a href="#tab-desc" data-toggle="tab">Mô tả</a></li>
          </ul>
          <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="tab-desc">
              <p><c:out value="${video.description}"/></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
