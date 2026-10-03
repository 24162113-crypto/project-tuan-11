<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="assets" value="${ctx}/static/assets"/>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><sitemesh:write property="title"/> - Quản trị</title>

  <!-- Metronic (Admin Dashboard) -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=latin,latin-ext,vietnamese" rel="stylesheet" type="text/css">
  <link href="${assets}/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link href="${assets}/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
  <link href="${assets}/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
  <link href="${assets}/global/css/components.css" id="style_components" rel="stylesheet" type="text/css">
  <link href="${assets}/global/css/plugins.css" rel="stylesheet" type="text/css">
  <link href="${assets}/admin/layout/css/layout.css" rel="stylesheet" type="text/css">
  <link href="${assets}/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color">
  <link href="${assets}/admin/layout/css/custom.css" rel="stylesheet" type="text/css">
  <!-- Tinh chỉnh riêng của VideoShare -->
  <link href="${ctx}/static/css/app.css" rel="stylesheet" type="text/css">
  <sitemesh:write property="head"/>
</head>
<body class="page-header-fixed page-quick-sidebar-over-content page-style-square role-admin">
  <%@ include file="admin-header.jspf" %>

  <div class="clearfix"></div>

  <!-- BEGIN CONTAINER -->
  <div class="page-container">
    <%@ include file="admin-sidebar.jspf" %>

    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
      <div class="page-content">
        <h3 class="page-title">
          <sitemesh:write property="meta.heading"/> <small><sitemesh:write property="meta.subtitle"/></small>
        </h3>
        <div class="page-bar">
          <ul class="page-breadcrumb">
            <li>
              <i class="fa fa-home"></i>
              <a href="${ctx}/admin/home">Quản trị</a>
              <i class="fa fa-angle-right"></i>
            </li>
            <li><a href="javascript:;"><sitemesh:write property="meta.heading"/></a></li>
          </ul>
        </div>

        <sitemesh:write property="body"/>
      </div>
    </div>
    <!-- END CONTENT -->
  </div>
  <!-- END CONTAINER -->

  <%@ include file="admin-footer.jspf" %>

  <!-- Javascript -->
  <script src="${assets}/global/plugins/jquery.min.js" type="text/javascript"></script>
  <script src="${assets}/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
  <script src="${assets}/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="${assets}/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
  <script src="${assets}/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
  <script src="${assets}/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
  <script src="${assets}/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
  <script src="${assets}/global/scripts/metronic.js" type="text/javascript"></script>
  <script src="${assets}/admin/layout/scripts/layout.js" type="text/javascript"></script>
  <script type="text/javascript">
    jQuery(document).ready(function () {
      Metronic.setAssetsPath('${assets}/');
      Metronic.init();
      Layout.init();
    });
  </script>
</body>
</html>
