<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="assets" value="${ctx}/static/assets"/>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title><sitemesh:write property="title"/> - VideoShare</title>

  <!-- Metronic (Frontend / Shop UI) -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700&amp;subset=latin,latin-ext,vietnamese" rel="stylesheet" type="text/css">
  <link href="${assets}/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="${assets}/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${assets}/global/css/components.css" rel="stylesheet">
  <link href="${assets}/frontend/layout/css/style.css" rel="stylesheet">
  <link href="${assets}/frontend/pages/css/style-shop.css" rel="stylesheet">
  <link href="${assets}/frontend/layout/css/style-responsive.css" rel="stylesheet">
  <link href="${assets}/frontend/layout/css/themes/blue.css" rel="stylesheet" id="style-color">
  <link href="${assets}/frontend/layout/css/custom.css" rel="stylesheet">
  <!-- Tinh chỉnh riêng của VideoShare -->
  <link href="${ctx}/static/css/app.css" rel="stylesheet">
  <sitemesh:write property="head"/>
</head>
<body class="ecommerce role-user">
  <%@ include file="header.jspf" %>

  <div class="title-wrapper">
    <div class="container"><div class="container-inner">
      <h1><sitemesh:write property="meta.heading"/></h1>
      <em><sitemesh:write property="meta.subtitle"/></em>
    </div></div>
  </div>

  <div class="main">
    <div class="container">
      <sitemesh:write property="body"/>
    </div>
  </div>

  <%@ include file="footer.jspf" %>

  <!-- Javascript -->
  <script src="${assets}/global/plugins/jquery.min.js" type="text/javascript"></script>
  <script src="${assets}/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
  <script src="${assets}/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="${assets}/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
  <script src="${assets}/frontend/layout/scripts/layout.js" type="text/javascript"></script>
  <script type="text/javascript">
    jQuery(document).ready(function () {
      Layout.init();
    });
  </script>
</body>
</html>
