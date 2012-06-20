<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="welcome to Open Mentor" /></title>
        <link rel="stylesheet" href="${resource(dir:'css/jqueryui/smoothness',file:'jquery-ui-1.8.12.custom.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css/jqueryui',file:'jquery.multiselect.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css/jqueryui',file:'jquery.multiselect.filter.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:javascript src='libs/jquery-1.5.1.min.js'/>
		<g:javascript>
		     jQuery.noConflict();
		</g:javascript>
        <g:javascript src='libs/jquery-ui-1.8.12.custom.min.js'/>
        <g:javascript src='libs/excanvas.min.js'/>
        <g:javascript src='libs/jquery.flot.js'/>
        <g:javascript src='libs/jquery.multiselect.min.js'/>
        <g:javascript src='libs/jquery.multiselect.filter.js'/>
        <g:layoutHead />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="logo"><img src="${resource(dir:'images',file:'OMLogo.jpg')}" alt="OpenMentor" border="0" />
                       <img src="${resource(dir:'images',file:'OMtitle.jpg')}" alt="OpenMentor" border="0" />
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       <img src="${resource(dir:'images',file:'kingslogo.jpg')}" alt="King's College" border="0" />
                       </div>
		<div id="versionContainer">
			<g:if env="test">Test</g:if>
			<g:if env="development">Development</g:if>
			<g:if env="staging">Staging</g:if>
			<g:message code="Version"/> <g:meta name="app.version"/>
		</div>
 		<div id='loginLinkContainer'>
 			<sec:ifLoggedIn>
			Logged in as <sec:username/> (<g:link controller='logout'>Logout</g:link>)
			</sec:ifLoggedIn>
			<sec:ifNotLoggedIn>
				<span><g:link controller='login' action='auth'>Login</g:link></span>
				<span style="margin-left:10px"><g:link controller='register' action="index">Register</g:link></span>
			</sec:ifNotLoggedIn>
		</div>
		<div class="nav">
        </div>
        
        <div id="nav">
        	<div class="homePagePanel">
                <div class="panelTop"></div>
                <div class="panelBody">
                    <h1>Navigation</h1>
                    <ul>
                    	<li>
                    		<a href="${createLinkTo(dir:'/',file:'')}">Home</a>
                    	</li>
                    	<g:if test="${! session.current_course}">
                    		<li>
                    			<g:link action="select" controller="course">
                    			Choose course
                    			</g:link>
                    		</li>
                    	</g:if>
                    	<g:else>
                    		<li>
                    			Using course: ${session.current_course}
                    			<g:link action="select" controller="course">
                    			(Change)
                    			</g:link>
                    		</li>
                    		<li>
                    			<g:link action="index" controller="assignment">
                    			Assignments
                    			</g:link>
                    		</li>
                    		<li>
                    			<g:link action="upload" controller="submission">
                    			Upload submissions
                    			</g:link>
                    		</li>
                    		<li>
                    			<g:link action="index" controller="report">
                    			View reports
                    			</g:link>
                    		</li>
                    	</g:else>
                    </ul>
                    
                    <hr/>
                    
                    <ul>
                    	<li>
                    		<g:link action="index" controller="help">
                    		Background
                    		</g:link>
                    	</li>
                    	    <li>
                    		<g:link action="index" controller="tutorials">
                    		Tutorials
                    		</g:link>
                    	</li>
                    	
                    	<%-- 
                    	<sec:ifAnyGranted roles="ROLE_OPENMENTOR-USER">
                    	<li>
                    		<g:link action="show" controller="user" id="${sec.username()}">
                    		My Account
                    		</g:link>
                    	</li>
                    	</sec:ifAnyGranted>
                    	--%>
                    </ul>
                    
                    <sec:ifAnyGranted roles="ROLE_OPENMENTOR-ADMIN,ROLE_OPENMENTOR-USER">
                    
                    <hr/>
                    
                    <ul>
                    	
                    	
                    	<li>
                    		<g:link action="index" controller="student">
                    		Students
                    	    </g:link>
                    	</li>
                    	<sec:ifAnyGranted roles="ROLE_OPENMENTOR-ADMIN">
                    	<li>
                    		<g:link action="index" controller="course">
                    		Courses
                    	    </g:link>
                    	</li>
                    	<li>
                    		<g:link action="index" controller="tutor">
                    		Tutors
                    	    </g:link>
                    	</li>
                    	</sec:ifAnyGranted>
						<%--
                    	<li>
                    		<g:link action="index" controller="user">
                    		Users
                    		</g:link>
                    	</li>
                    	--%>
                    </ul>
                    
                    </sec:ifAnyGranted>

                    <sec:ifAnyGranted roles="ROLE_OPENMENTOR-ADMIN">
                    
                    <hr/>
                    
                    <ul>
                    	<li>
                    		<g:link action="index" controller="history">
                    		History
                    	    </g:link>
                    	</li>
					</ul>
					
					</sec:ifAnyGranted>
                </div>
                <div class="panelBtm">
                
                </div>
            </div>
            <g:pageProperty name="page.navpanel" />
        </div>
        
        <g:layoutBody />

        <div id="jqdialog"></div>
        <div id="postJQuery">
            <g:pageProperty name="page.postJQuery" />
        </div>
        <div id="footer" align="center">
        <p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>
        <img src="${resource(dir:'images',file:'logofooter.jpg')}" alt="University of Southampton, King's College, Open University and JISC" border="0" />
        </div>
    </body>
</html>