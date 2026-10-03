<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>Thanh toán</title>
  <meta name="heading" content="Thanh toán đơn hàng">
  <meta name="subtitle" content="Thanh toán khi nhận hàng (COD)">
</head>
<body>
  <ul class="breadcrumb">
    <li><a href="${ctx}/home">Trang chủ</a></li>
    <li><a href="${ctx}/cart">Giỏ hàng</a></li>
    <li class="active">Thanh toán</li>
  </ul>

  <div class="row margin-bottom-40">
    <div class="col-md-7 col-sm-7">
      <c:if test="${not empty error}">
        <div class="alert alert-danger"><c:out value="${error}"/></div>
      </c:if>
      <form class="form-horizontal" role="form" method="post" action="${ctx}/checkout">
        <div class="form-group">
          <label for="receiver" class="col-lg-3 control-label">Người nhận <span class="require">*</span></label>
          <div class="col-lg-9">
            <input id="receiver" type="text" class="form-control" name="receiver" maxlength="100" value="<c:out value='${receiver}'/>" required>
          </div>
        </div>
        <div class="form-group">
          <label for="phone" class="col-lg-3 control-label">Điện thoại <span class="require">*</span></label>
          <div class="col-lg-9">
            <input id="phone" type="text" class="form-control" name="phone" maxlength="15" value="<c:out value='${phone}'/>" required>
          </div>
        </div>
        <div class="form-group">
          <label for="address" class="col-lg-3 control-label">Địa chỉ <span class="require">*</span></label>
          <div class="col-lg-9">
            <input id="address" type="text" class="form-control" name="address" maxlength="255" value="<c:out value='${address}'/>" required>
          </div>
        </div>
        <div class="form-group">
          <label for="note" class="col-lg-3 control-label">Ghi chú</label>
          <div class="col-lg-9">
            <textarea id="note" class="form-control" name="note" rows="3" maxlength="255"><c:out value="${note}"/></textarea>
          </div>
        </div>
        <div class="form-group">
          <label class="col-lg-3 control-label">Thanh toán</label>
          <div class="col-lg-9">
            <div class="radio-list">
              <label><input type="radio" name="paymentMethod" value="COD" checked> Thanh toán khi nhận hàng (COD)</label>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-9 col-lg-offset-3">
            <button type="submit" class="btn btn-primary">Đặt hàng</button>
            <a class="btn btn-default" href="${ctx}/cart">Quay lại giỏ hàng</a>
          </div>
        </div>
      </form>
    </div>

    <div class="col-md-5 col-sm-5">
      <h3 class="checkout-heading">Đơn hàng của bạn</h3>
      <table class="table table-bordered cart-table">
        <tbody>
          <c:forEach var="it" items="${cart.items}">
            <tr>
              <td><c:out value="${it.title}"/><div class="text-muted small">${it.priceText} &times; ${it.quantity}</div></td>
              <td class="text-right">${it.subtotalText}</td>
            </tr>
          </c:forEach>
        </tbody>
        <tfoot>
          <tr>
            <td class="text-right"><strong>Tổng cộng</strong></td>
            <td class="text-right cart-total">${cart.totalAmountText}</td>
          </tr>
        </tfoot>
      </table>
    </div>
  </div>
</body>
</html>
