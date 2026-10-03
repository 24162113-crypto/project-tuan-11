<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>Quản lý Video</title>
  <meta name="heading" content="Quản lý Video">
  <meta name="subtitle" content="Danh sách, thêm, sửa và xóa video">
</head>
<body>
  <div class="row">
    <div class="col-md-12">
      <div class="portlet box blue">
        <div class="portlet-title">
          <div class="caption"><i class="fa fa-film"></i>Danh sách video</div>
          <div class="actions">
            <a class="btn btn-default btn-sm" href="${ctx}/admin/videos/add"><i class="fa fa-plus"></i> Thêm video</a>
          </div>
        </div>
        <div class="portlet-body">
          <div class="table-scrollable">
            <table class="table table-striped table-bordered table-hover video-table">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Poster</th>
                  <th>Mã video</th>
                  <th>Tiêu đề</th>
                  <th>Danh mục</th>
                  <th>Giá</th>
                  <th>Tồn kho</th>
                  <th>Lượt xem</th>
                  <th>Share</th>
                  <th>Like</th>
                  <th>Trạng thái</th>
                  <th>Thao tác</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="v" items="${result.items}" varStatus="st">
                  <tr>
                    <td>${(result.page - 1) * 6 + st.count}</td>
                    <td><img class="thumb" src="${ctx}/image?name=${empty v.poster ? 'no-image.svg' : v.poster}" alt="poster"></td>
                    <td><c:out value="${v.videoId}"/></td>
                    <td><c:out value="${v.title}"/></td>
                    <td><c:out value="${v.categoryName}"/></td>
                    <td>${v.priceText}</td>
                    <td>${v.stock}</td>
                    <td>${v.views}</td>
                    <td>${v.shares}</td>
                    <td>${v.likes}</td>
                    <td><span class="label label-sm ${v.active ? 'label-success' : 'label-default'}">${v.active ? 'Hiển thị' : 'Ẩn'}</span></td>
                    <td class="row-actions">
                      <a class="btn btn-xs blue" href="${ctx}/admin/videos/edit?id=${v.videoId}"><i class="fa fa-edit"></i> Sửa</a>
                      <form method="post" action="${ctx}/admin/videos/delete" onsubmit="return confirm('Bạn có chắc muốn xóa video này?');">
                        <input type="hidden" name="id" value="<c:out value='${v.videoId}'/>">
                        <input type="hidden" name="page" value="${result.page}">
                        <button class="btn btn-xs red" type="submit"><i class="fa fa-trash-o"></i> Xóa</button>
                      </form>
                    </td>
                  </tr>
                </c:forEach>
                <c:if test="${empty result.items}">
                  <tr><td colspan="12" class="text-muted text-center">Chưa có video nào.</td></tr>
                </c:if>
              </tbody>
            </table>
          </div>

          <div class="text-center">
            <ul class="pagination">
              <li class="${result.page > 1 ? '' : 'disabled'}"><a href="${ctx}/admin/videos?page=${result.page > 1 ? result.page - 1 : 1}">&laquo;</a></li>
              <c:forEach begin="1" end="${result.totalPages}" var="i">
                <li class="${i == result.page ? 'active' : ''}"><a href="${ctx}/admin/videos?page=${i}">${i}</a></li>
              </c:forEach>
              <li class="${result.page < result.totalPages ? '' : 'disabled'}"><a href="${ctx}/admin/videos?page=${result.page < result.totalPages ? result.page + 1 : result.totalPages}">&raquo;</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
