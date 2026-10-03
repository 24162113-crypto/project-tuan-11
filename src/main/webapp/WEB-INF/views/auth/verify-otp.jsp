<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Kích hoạt tài khoản</title>
  <meta name="heading" content="Kích hoạt tài khoản">
  <meta name="subtitle" content="Nhập mã OTP đã được gửi tới email của bạn">
</head>
<body>
  <ul class="breadcrumb">
    <li><a href="${pageContext.request.contextPath}/home">Trang chủ</a></li>
    <li><a href="${pageContext.request.contextPath}/register">Đăng ký</a></li>
    <li class="active">Kích hoạt tài khoản</li>
  </ul>

  <div class="row margin-bottom-40">
    <div class="col-md-12 col-sm-12">
      <div class="content-form-page">
        <div class="row">
          <div class="col-md-7 col-sm-7">
            <p class="text-muted">Mã OTP gồm 6 số đã được gửi tới email bạn đăng ký.</p>
            <c:if test="${not empty error}">
              <div class="alert alert-danger"><c:out value="${error}"/></div>
            </c:if>
            <c:if test="${not empty info}">
              <div class="alert alert-success"><c:out value="${info}"/></div>
            </c:if>
            <form class="form-horizontal form-without-legend" role="form" method="post" action="${pageContext.request.contextPath}/verify-otp">
              <div class="form-group">
                <label for="otp" class="col-lg-4 control-label">Mã OTP <span class="require">*</span></label>
                <div class="col-lg-8">
                  <input id="otp" type="text" class="form-control" name="otp" maxlength="6" pattern="[0-9]{6}" inputmode="numeric" autocomplete="one-time-code" required>
                </div>
              </div>
              <div class="row">
                <div class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
                  <button type="submit" class="btn btn-primary">Xác nhận</button>
                </div>
              </div>
            </form>
            <form class="form-horizontal form-without-legend" role="form" method="post" action="${pageContext.request.contextPath}/verify-otp">
              <input type="hidden" name="action" value="resend">
              <div class="row">
                <div class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-10">
                  <button type="submit" class="btn btn-default">Gửi lại mã OTP</button>
                </div>
              </div>
            </form>
          </div>
          <div class="col-md-4 col-sm-4 pull-right">
            <div class="form-info">
              <h2><em>Chưa nhận</em> được mã?</h2>
              <p>Hãy kiểm tra cả thư mục thư rác (spam). Bạn có thể bấm "Gửi lại mã OTP" để nhận mã mới.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
