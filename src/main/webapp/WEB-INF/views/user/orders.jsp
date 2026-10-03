<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>Đơn hàng của tôi</title>
  <meta name="heading" content="Đơn hàng của tôi">
  <meta name="subtitle" content="Lịch sử các đơn hàng đã đặt">
</head>
<body>
  <ul class="breadcrumb">
    <li><a href="${ctx}/home">Trang chủ</a></li>
    <li class="active">Đơn hàng của tôi</li>
  </ul>

  <div class="row margin-bottom-40">
    <div class="col-md-12">
      <ul class="nav nav-tabs order-status-tabs">
        <li class="${empty currentStatus ? 'active' : ''}">
          <a href="${ctx}/orders">Tất cả <span class="badge">${allCount}</span></a>
        </li>
        <c:forEach var="st" items="${statuses}">
          <li class="${currentStatus == st.code ? 'active' : ''}">
            <a href="${ctx}/orders?status=${st.code}">${st.text} <span class="badge">${empty counts[st.code] ? 0 : counts[st.code]}</span></a>
          </li>
        </c:forEach>
      </ul>
      <br>

      <c:choose>
        <c:when test="${empty orders}">
          <div class="cart-empty">
            <c:choose>
              <c:when test="${empty currentStatus}"><p>Bạn chưa có đơn hàng nào.</p></c:when>
              <c:otherwise><p>Không có đơn hàng nào ở trạng thái "${currentStatusText}".</p></c:otherwise>
            </c:choose>
            <c:if test="${not empty currentStatus}">
              <a class="btn btn-default" href="${ctx}/orders">Xem tất cả đơn hàng</a>
            </c:if>
            <a class="btn btn-primary" href="${ctx}/home">Mua sắm ngay</a>
          </div>
        </c:when>
        <c:otherwise>
          <div class="table-responsive">
            <table class="table table-bordered table-hover cart-table">
              <thead>
                <tr>
                  <th>Mã đơn</th>
                  <th>Ngày đặt</th>
                  <th class="text-center">Số lượng</th>
                  <th class="text-right">Tổng tiền</th>
                  <th>Thanh toán</th>
                  <th>Trạng thái</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="o" items="${orders}">
                  <tr>
                    <td>#${o.orderId}</td>
                    <td>${o.createdAtText}</td>
                    <td class="text-center">${o.itemCount}</td>
                    <td class="text-right">${o.totalText}</td>
                    <td>${o.paymentText}</td>
                    <td><span class="label ${o.statusClass}">${o.statusText}</span></td>
                    <td><a class="btn btn-default btn-sm" href="${ctx}/order?id=${o.orderId}">Chi tiết</a></td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</body>
</html>
