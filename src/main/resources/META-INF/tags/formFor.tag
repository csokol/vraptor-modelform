<%@ tag pageEncoding="UTF-8" %>
<%@attribute name="form" type="br.com.caelum.vraptor.modelform.ModelForm" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form method="post">
    <c:forEach items="${form.fields}" var="f">
        <label> ${f.name}:
            ${form.inputFor(f)}
        </label>
    </c:forEach>

    <input type="submit">
</form>
