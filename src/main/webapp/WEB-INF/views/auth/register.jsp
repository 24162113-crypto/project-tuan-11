<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Đăng ký</title>
  <meta name="heading" content="Đăng ký tài khoản">
  <meta name="subtitle" content="Tạo tài khoản mới chỉ với vài bước đơn giản">
</head>
<body>
  <ul class="breadcrumb">
    <li><a href="${pageContext.request.contextPath}/home">Trang chủ</a></li>
    <li class="active">Đăng ký</li>
  </ul>

  <div class="row margin-bottom-40">
    <div class="col-md-12 col-sm-12">
      <div class="content-form-page">
        <div class="row">
          <div class="col-md-7 col-sm-7">
            <c:if test="${not empty error}">
              <div class="alert alert-danger"><c:out value="${error}"/></div>
            </c:if>
            <form class="form-horizontal form-without-legend" role="form" method="post" action="${pageContext.request.contextPath}/register">
              <div class="form-group">
                <label for="username" class="col-lg-4 control-label">Tên đăng nhập <span class="require">*</span></label>
                <div class="col-lg-8">
                  <input id="username" type="text" class="form-control" name="username" maxlength="50" value="<c:out value='${form.username}'/>" required>
                </div>
              </div>
              <div class="form-group">
                <label for="fullname" class="col-lg-4 control-label">Họ và tên <span class="require">*</span></label>
                <div class="col-lg-8">
                  <input id="fullname" type="text" class="form-control" name="fullname" maxlength="50" value="<c:out value='${form.fullname}'/>" required>
                </div>
              </div>
              <div class="form-group">
                <label for="email" class="col-lg-4 control-label">Email (nhận mã OTP) <span class="require">*</span></label>
                <div class="col-lg-8">
                  <input id="email" type="email" class="form-control" name="email" maxlength="150" value="<c:out value='${form.email}'/>" required>
                </div>
              </div>
              <div class="form-group">
                <label for="phone" class="col-lg-4 control-label">Số điện thoại</label>
                <div class="col-lg-8">
                  <input id="phone" type="text" class="form-control" name="phone" maxlength="15" value="<c:out value='${form.phone}'/>">
                </div>
              </div>
              <div class="form-group">
                <label for="password" class="col-lg-4 control-label">Mật khẩu <span class="require">*</span></label>
                <div class="col-lg-8">
                  <input id="password" type="password" class="form-control" name="password" maxlength="50" required>
                </div>
              </div>
              <div class="form-group">
                <label for="confirm" class="col-lg-4 control-label">Xác nhận mật khẩu <span class="require">*</span></label>
                <div class="col-lg-8">
                  <input id="confirm" type="password" class="form-control" name="confirm" maxlength="50" required>
                </div>
              </div>
              <div class="row">
                <div class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
                  <button type="submit" class="btn btn-primary">Đăng ký</button>
                </div>
              </div>
            </form>
          </div>
          <div class="col-md-4 col-sm-4 pull-right">
            <div class="form-info">
              <h2><em>Đã có</em> tài khoản?</h2>
              <p>Mã OTP kích hoạt gồm 6 số sẽ được gửi tới email bạn đăng ký. Nếu đã có tài khoản, hãy đăng nhập ngay.</p>
              <a class="btn btn-default" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
