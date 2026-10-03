<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>Trang quản trị</title>
  <meta name="heading" content="Bảng điều khiển">
  <meta name="subtitle" content="Tổng quan hệ thống">
</head>
<body>
  <div class="note note-info">
    <p>Xin chào, <strong><c:out value="${sessionScope.currentUser.fullname}"/></strong>. Chào mừng bạn đến với trang quản trị VideoShare.</p>
  </div>

  <div class="row">
    <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
      <div class="dashboard-stat blue-madison">
        <div class="visual"><i class="fa fa-film"></i></div>
        <div class="details">
          <div class="number">${totalVideos}</div>
          <div class="desc">Video</div>
        </div>
        <a class="more" href="${ctx}/admin/videos">Quản lý Video <i class="m-icon-swapright m-icon-white"></i></a>
      </div>
    </div>
    <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
      <div class="dashboard-stat green-haze">
        <div class="visual"><i class="fa fa-th-large"></i></div>
        <div class="details">
          <div class="number">${totalCategories}</div>
          <div class="desc">Danh mục</div>
        </div>
        <a class="more" href="${ctx}/home#san-pham">Xem trên trang chủ <i class="m-icon-swapright m-icon-white"></i></a>
      </div>
    </div>
  </div>

  <p><a class="btn blue" href="${ctx}/admin/videos"><i class="fa fa-film"></i> Quản lý Video</a></p>
</body>
</html>
