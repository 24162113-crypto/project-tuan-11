<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>${mode == 'edit' ? 'Cập nhật video' : 'Thêm video'}</title>
  <meta name="heading" content="${mode == 'edit' ? 'Cập nhật video' : 'Thêm video mới'}">
  <meta name="subtitle" content="Nhập thông tin video">
</head>
<body>
  <div class="row">
    <div class="col-md-8 col-lg-7">
      <div class="portlet box blue">
        <div class="portlet-title">
          <div class="caption"><i class="fa fa-film"></i>${mode == 'edit' ? 'Cập nhật video' : 'Thêm video mới'}</div>
        </div>
        <div class="portlet-body form">
          <c:if test="${not empty error}">
            <div class="alert alert-danger form-alert"><c:out value="${error}"/></div>
          </c:if>
          <form class="form-horizontal" role="form" method="post" action="${ctx}/admin/videos/save" enctype="multipart/form-data">
            <input type="hidden" name="mode" value="${mode}">
            <div class="form-body">
              <div class="form-group">
                <label for="videoId" class="col-md-3 control-label">Mã video</label>
                <div class="col-md-9">
                  <input id="videoId" type="text" class="form-control" name="videoId" maxlength="50" value="<c:out value='${video.videoId}'/>" ${mode == 'edit' ? 'readonly' : ''} placeholder="${mode == 'edit' ? '' : 'Để trống để tự sinh mã'}">
                </div>
              </div>
              <div class="form-group">
                <label for="title" class="col-md-3 control-label">Tiêu đề</label>
                <div class="col-md-9">
                  <input id="title" type="text" class="form-control" name="title" maxlength="200" value="<c:out value='${video.title}'/>" required>
                </div>
              </div>
              <div class="form-group">
                <label for="categoryId" class="col-md-3 control-label">Danh mục</label>
                <div class="col-md-9">
                  <select id="categoryId" class="form-control" name="categoryId" required>
                    <option value="">-- Chọn danh mục --</option>
                    <c:forEach var="cat" items="${categories}">
                      <option value="${cat.categoryId}" ${video.category.categoryId == cat.categoryId ? 'selected' : ''}><c:out value="${cat.categoryname}"/></option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="posterFile" class="col-md-3 control-label">Poster</label>
                <div class="col-md-9">
                  <c:if test="${not empty video.poster}">
                    <img class="thumb large" src="${ctx}/image?name=${video.poster}" alt="poster">
                  </c:if>
                  <input id="posterFile" type="file" name="posterFile" accept="image/png,image/jpeg,image/gif,image/webp">
                </div>
              </div>
              <div class="form-group">
                <label for="views" class="col-md-3 control-label">Lượt xem</label>
                <div class="col-md-9">
                  <input id="views" type="number" class="form-control" min="0" name="views" value="${empty video.views ? 0 : video.views}">
                </div>
              </div>
              <div class="form-group">
                <label for="price" class="col-md-3 control-label">Giá (₫)</label>
                <div class="col-md-9">
                  <input id="price" type="number" class="form-control" min="0" name="price" value="${empty video.price ? 0 : video.price}">
                </div>
              </div>
              <div class="form-group">
                <label for="stock" class="col-md-3 control-label">Tồn kho</label>
                <div class="col-md-9">
                  <input id="stock" type="number" class="form-control" min="0" name="stock" value="${empty video.stock ? 0 : video.stock}">
                </div>
              </div>
              <div class="form-group">
                <label for="description" class="col-md-3 control-label">Mô tả</label>
                <div class="col-md-9">
                  <textarea id="description" class="form-control" name="description" rows="4" maxlength="500"><c:out value="${video.description}"/></textarea>
                </div>
              </div>
              <div class="form-group">
                <div class="col-md-offset-3 col-md-9">
                  <div class="checkbox-list">
                    <label class="checkbox-inline"><input type="checkbox" name="active" ${video.active ? 'checked' : ''}> Hiển thị</label>
                  </div>
                </div>
              </div>
            </div>
            <div class="form-actions">
              <div class="row">
                <div class="col-md-offset-3 col-md-9">
                  <button class="btn blue" type="submit"><i class="fa fa-check"></i> Lưu</button>
                  <a class="btn default" href="${ctx}/admin/videos">Hủy</a>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
