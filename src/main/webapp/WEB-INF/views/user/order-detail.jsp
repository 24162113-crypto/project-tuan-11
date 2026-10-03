<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>Đơn hàng #${order.orderId}</title>
  <meta name="heading" content="Đơn hàng #${order.orderId}">
  <meta name="subtitle" content="Chi tiết đơn hàng">
</head>
<body>
  <ul class="breadcrumb">
    <li><a href="${ctx}/home">Trang chủ</a></li>
    <li><a href="${ctx}/orders">Đơn hàng của tôi</a></li>
    <li class="active">#${order.orderId}</li>
  </ul>

  <c:if test="${placed}">
    <div class="alert alert-success">Đặt hàng thành công! Bạn sẽ thanh toán khi nhận hàng.</div>
  </c:if>

  <c:choose>
    <c:when test="${order.statusStep > 0}">
      <c:set var="step" value="${order.statusStep}"/>
      <ol class="breadcrumb order-progress">
        <li class="${step >= 1 ? 'active' : ''}"><strong>Đơn hàng mới</strong></li>
        <li class="${step >= 2 ? 'active' : ''}">${step >= 2 ? '<strong>Đã xác nhận</strong>' : 'Đã xác nhận'}</li>
        <li class="${step >= 3 ? 'active' : ''}">${step >= 3 ? '<strong>Chuẩn bị hàng</strong>' : 'Chuẩn bị hàng'}</li>
        <li class="${step >= 4 ? 'active' : ''}">${step >= 4 ? '<strong>Vận chuyển</strong>' : 'Vận chuyển'}</li>
        <li class="${step >= 5 ? 'active' : ''}">${step >= 5 ? '<strong>Giao hàng</strong>' : 'Giao hàng'}</li>
        <li class="${step >= 6 ? 'active' : ''}">${step >= 6 ? '<strong>Đã giao</strong>' : 'Đã giao'}</li>
      </ol>
    </c:when>
    <c:when test="${order.statusEnum.code == 'CANCELLED'}">
      <div class="alert alert-danger">Đơn hàng này đã bị hủy.</div>
    </c:when>
    <c:when test="${order.statusEnum.code == 'RETURNED'}">
      <div class="alert alert-warning">Đơn hàng này đã được hoàn trả.</div>
    </c:when>
  </c:choose>

  <div class="row margin-bottom-40">
    <div class="col-md-5">
      <ul class="list-unstyled video-meta order-info">
        <li>Mã đơn: <strong>#${order.orderId}</strong></li>
        <li>Ngày đặt: ${order.createdAtText}</li>
        <li>Trạng thái: <span class="label ${order.statusClass}">${order.statusText}</span></li>
        <li>Thanh toán: ${order.paymentText}</li>
        <li>Người nhận: <c:out value="${order.receiver}"/></li>
        <li>Điện thoại: <c:out value="${order.phone}"/></li>
        <li>Địa chỉ: <c:out value="${order.address}"/></li>
        <c:if test="${not empty order.note}">
          <li>Ghi chú: <c:out value="${order.note}"/></li>
        </c:if>
      </ul>
    </div>
    <div class="col-md-7">
      <div class="table-responsive">
        <table class="table table-bordered cart-table">
          <thead>
            <tr>
              <th>Sản phẩm</th>
              <th class="text-right">Đơn giá</th>
              <th class="text-center">SL</th>
              <th class="text-right">Thành tiền</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="it" items="${order.items}">
              <tr>
                <td><c:out value="${it.title}"/></td>
                <td class="text-right">${it.priceText}</td>
                <td class="text-center">${it.quantity}</td>
                <td class="text-right">${it.subtotalText}</td>
              </tr>
            </c:forEach>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="3" class="text-right"><strong>Tổng cộng</strong></td>
              <td class="text-right cart-total">${order.totalText}</td>
            </tr>
          </tfoot>
        </table>
      </div>
      <a class="btn btn-default" href="${ctx}/orders">&laquo; Đơn hàng của tôi</a>
      <a class="btn btn-primary" href="${ctx}/home">Tiếp tục mua sắm</a>
    </div>
  </div>
</body>
</html>
