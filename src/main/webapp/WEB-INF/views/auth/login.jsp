<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Đăng nhập</title>
  <meta name="heading" content="Đăng nhập">
  <meta name="subtitle" content="Đăng nhập để chia sẻ và theo dõi video của bạn">
</head>
<body>
  <ul class="breadcrumb">
    <li><a href="${pageContext.request.contextPath}/home">Trang chủ</a></li>
    <li class="active">Đăng nhập</li>
  </ul>

  <div class="row margin-bottom-40">
    <div class="col-md-12 col-sm-12">
      <div class="content-form-page">
        <div class="row">
          <div class="col-md-7 col-sm-7">
            <c:if test="${param.registered == '1'}">
              <div class="alert alert-success">Kích hoạt tài khoản thành công, hãy đăng nhập.</div>
            </c:if>
            <c:if test="${param.need == '1'}">
              <div class="alert alert-info">Vui lòng đăng nhập để thanh toán đơn hàng.</div>
            </c:if>
            <c:if test="${not empty error}">
              <div class="alert alert-danger"><c:out value="${error}"/></div>
            </c:if>
            <form class="form-horizontal form-without-legend" role="form" method="post" action="${pageContext.request.contextPath}/login">
              <div class="form-group">
                <label for="username" class="col-lg-4 control-label">Tên đăng nhập <span class="require">*</span></label>
                <div class="col-lg-8">
                  <input id="username" type="text" class="form-control" name="username" value="<c:out value='${username}'/>" required>
                </div>
              </div>
              <div class="form-group">
                <label for="password" class="col-lg-4 control-label">Mật khẩu <span class="require">*</span></label>
                <div class="col-lg-8">
                  <input id="password" type="password" class="form-control" name="password" required>
                </div>
              </div>
              <div class="row">
                <div class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
                  <button type="submit" class="btn btn-primary">Đăng nhập</button>
                </div>
              </div>
            </form>
          </div>
          <div class="col-md-4 col-sm-4 pull-right">
            <div class="form-info">
              <h2><em>Chưa có</em> tài khoản?</h2>
              <p>Đăng ký miễn phí để chia sẻ, theo dõi và quản lý những video bạn yêu thích.</p>
              <a class="btn btn-default" href="${pageContext.request.contextPath}/register">Đăng ký</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
