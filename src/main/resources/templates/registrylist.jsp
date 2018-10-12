<%@include file="header.jsp" %>

<div class="content">
    <div class="container-fluid">


        <div class="col-md-12">
            <div class="card card-plain">
                <div class="card-header card-header-primary">
                    <h4 class="card-title mt-0">Council Registry List</h4>
                    <p class="card-category"> Details</p>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead class="">

                            <th>Name</th>
                            <th>Schema Name</th>
                            <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                <th width="100"></th>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ADMIN')">
                                <th width="100"></th>
                            </sec:authorize>
                            </thead>
                           <tbody>

                           <c:forEach items="${registryCredentialsList}" var="registry">
                               <tr>
                                   <td>${registry.name}</td>
                                   <td>${registry.dbUserName}</td>
                                   <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                       <td><a href="<c:url value='/registry/edit-registry-${registry.name}' />"
                                              class="btn btn-success custom-width">edit</a>
                                       </td>
                                   </sec:authorize>
                                   <sec:authorize access="hasRole('ADMIN')">
                                       <td><a href="<c:url value='/registry/delete-registry-${registry.name}' />"
                                              class="btn btn-danger custom-width">delete</a>
                                       </td>
                                   </sec:authorize>
                                   <sec:authorize access="hasRole('ADMIN')">
                                       <td><a href="<c:url value='/registry/sync-registry-${registry.name}' />"
                                              id="myBtn"  class="btn btn-danger custom-width">sync</a>
                                       </td>
                                   </sec:authorize>
                               </tr>
                           </c:forEach>

                           </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>

<%@include file="footer.jsp" %>