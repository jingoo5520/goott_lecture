<%@page import="java.sql.Connection"%>
<%@page import="com.ajaxjsp.dao.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<title>Insert title here</title>
<script type="text/javascript">
   let empData = null;
   let jobData = null;
   let deptData = null;
   
   $(document).ready(function() {
      getAllEmployees("employee_id");
      getAllJobs();
      getAllDepartments();
      
      $("#writeIcon").click(function(){
    	  $("#myModal").show();
      })
      
      $(".writeBtnClose").click(function(){
    	  $("#myModal").hide();
      })
      
      
      $("#job_id").change(function() {
         makeSalInput($(this).val());
      })
      
      $("#salaryBar").change(function() {
         $("#salary").val($(this).val());
      })
      
      $(".writeBtn").click(function () {
    	  let firstName = $("#first_name").val();
    	  let lastName = $("#last_name").val();
    	  let email = $("#email").val();
    	  let phoneNumber = $("#phone_number").val();
    	  let hireDate = $("#hire_date").val();
    	  let jobId = $("#job_id").val();
    	  let salary = $("#salary").val();
    	  let commissionPct = $("#commission_pct").val() == "" ? 0 : $("#commission_pct").val();
    	  let manager = $("#manager_id").val();
    	  let department = $("#department_id").val();
    	  
    	  let tempEmp = {
    		firstName: firstName,
    		lastName: lastName,
    		email: email,
    		phoneNumber: phoneNumber,
    		hireDate: hireDate,
    		jobId: jobId,
    		salary: salary,
    		commissionPct: commissionPct,
    		manager: manager,
    		department: department,
    	  }
    	  
    	  console.log(tempEmp);
    	  
    	  inputEmpValidate(tempEmp);
      })
      
      // 이름으로 사원 검색하기(Enter)
      $("#findEmpName").keyup(function(e){
    	  let searchName = $(this).val().toLowerCase();
    	  
    	   if(e.keyCode == 13){
    		   $("#orderEmpNo").prop('checked', true);
    		   
    		  $.ajax({
 		         url : "findEmpByName.do",
 		         type: "GET",
 		         data: {
 		        	 "searchName" : searchName,
 		        	 "orderMethod" : "employee_id",
 		         },
 		         dataType : "json",
 		         success: function(data) {
 		           empData = data;
 		           outputEntireEmployees(data);
 		         },
 		         error: function(e) {
 		        	 console.log(e);
 		         },
 		         complete: function() {
 		         }
 		      });
    	  } 
      })
      
      // 정렬 방식 변경
		$(".orderMethod").click(function(){
			let orderMethod = $(this).val();
			let searchName = $("#findEmpName").val();
			
			console.log("searchName:" + searchName);
			console.log("orderMethod:" + orderMethod);
			
			if(searchName != ""){
				$.ajax({
	 		         url : "findEmpByName.do",
	 		         type: "GET",
	 		         data: {
	 		        	"orderMethod" : orderMethod,
	 		        	"searchName" : searchName,
	 		         },
	 		         dataType : "json",
	 		         success: function(data) {
	 		        	 outputEntireEmployees(data);
	 		         },
	 		         error: function(e) {
	 		        	
	 		         },
	 		         complete: function() {
	 		         }
	 		      });	
			} else {
				$.ajax({
	 		         url : "employees",
	 		         type: "GET",
	 		         data: {
	 		        	 "orderMethod" : orderMethod,
	 		         },
	 		         dataType : "json",
	 		         success: function(data) {
	 		        	outputEntireEmployees(data);
	 		         },
	 		         error: function(e) {
	 		        	
	 		         },
	 		         complete: function() {
	 		         }
	 		      });	
			}
			
			 
		})
      
   });
   
	function inputEmpValidate(tempEmp){
	   // 사원 저장시 유효성 검사
	   let islastNameValid = checkLastNameValid(tempEmp.lastName);
	   let isEmailValid = checkEmailValid(tempEmp.email);
	   let isHireDateValid = checkHireDateValid(tempEmp.hireDate);
	   let isJobIdValid = checkJobIdValid(tempEmp.jobId);
	   let isManagerValid = checkManagerValid(tempEmp.manager);
	   let isDepartmentValid = checkDepartmentValid(tempEmp.department);
	   // 모두 true인 경우 ajax 호출
	
	   console.log(islastNameValid);
	   console.log(isEmailValid);
	   console.log(isHireDateValid);
	   console.log(isJobIdValid);
	   console.log(isManagerValid);
	   console.log(isDepartmentValid);
	   
	   if(islastNameValid && isEmailValid && isHireDateValid && isJobIdValid && isManagerValid && isDepartmentValid){
		   let url = "saveEmp.do";
		      $.ajax({
		         url : url,
		         type: "GET",
		         data: tempEmp,
		         dataType : "json",
		         success: function(response) {
		            console.log(response);
		         },
		         error: function(e) {
		            console.log(e);
		         },
		         complete: function() {
		            
		         }
		      });
		   
	   } 
   	}
   
   	function checkLastNameValid(lastName) {
	   	let result = false;
	   	
	  	if(lastName != null && lastName != ""){
			result = true;
	   	}
	   
	   	return result;
   	}
   
	function checkEmailValid(email) {
		let result = false;
		let isDuplicated = false;
		
		// 중복체크는 DB 다녀오는게 맞음
		// 추가중 다른 추가가 발생할 수 있기 때문에
		for(employee of empData.datas){
			if(email.toUpperCase() == employee.email){
				isDuplicated = true;
				break;
			}
		}
		
		if(email != "" && !isDuplicated){
			result = true;
		}
	   
	   	return result;
   	}
   
   	function checkHireDateValid(hireDate) {
		let result = false;
		
		if(hireDate != null && hireDate != ""){
			result = true;
		}
	   
	   	return result;
   	}
   
   function checkJobIdValid(jobId) {
		let result = false;
		   
		if(jobId != null && jobId != ""){
			result = true;
		}
	   
	   	return result;
   }
   
   function checkManagerValid(manager){
	   	let result = false;
	   
		if(manager != ""){
			result = true;
		}
	   
	   	return result;
   }
   
   function checkDepartmentValid(department){
	   let result = false;
	   
		if(department != ""){
			result = true;
		}
	   
	   	return result;
   }
   /* check 종료 */
   
   function getAllEmployees(orderMethod) {
      let url = "employees";
      $.ajax({
         url : url,
         type: "GET",
         dataType : "json",
         data: {"orderMethod" : orderMethod},
         success: function(response) {
            empData = response;
            outputEntireEmployees(response);
            addSelect(response, "manager_id");
         },
         error: function(e) {
            console.log(e);
         },
         complete: function() {
            
         }
      });
   }
   
   function getAllJobs() {
      let url = "jobs"
      $.ajax({
         url: url,
         type: "GET",
         dataType: "json",
         success: function(response) {
            jobData = response;
            addSelect(response, "job_id");
         },
         error: function(e) {
            console.log(e);
         }
      })
   }
   
   function getAllDepartments() {
      let url = "departments";
      $.ajax({
         url: url,
         type: "GET",
         dataType: "json",
         success: function(response) {
            deptData = response;
            addSelect(response, "department_id");
         },
         error: function(e) {
            console.log(e);
         }
      })
   }
   
   function outputEntireEmployees(json) {
		// 모든 직원 데이터 파싱 및 출력
		$("#outputDate").html(`\${json.outputDate}`);
		$("#outputCnt").html(`총 사원 : \${json.size}`);
		let output = `
	      <table class="table table-striped">
	         <thead>
	            <tr>
	               <th>사원번호</th>
	               <th>이름</th>
	               <th>이메일</th>
	               <th>전화번호</th>
	               <th>입사일</th>
	               <th>직급</th>
	               <th>급여</th>
	               <th>커미션 (%)</th>
	               <th>매니저</th>
	               <th>부서명</th>
	               <th>수정</th>
	               <th>삭제</th>
	            </tr>
	         </thead>
	         <tbody>`;
		$.each(json.datas, function(i, item) {
			let managerId = item.manager_id;
			let managerName = "";
			$.each(empData.datas, function(i, mitem) {
				if (managerId == mitem.employee_id) {
					managerName = mitem.last_name + " " + mitem.first_name
				}
			})
			output += `<tr>
	               <td>\${item.employee_id}</td>
	               <td>\${item.last_name} \${item.first_name}</td>
	               <td>\${item.email}</td>
	               <td>\${item.phone_number}</td>
	               <td>\${item.hire_date}</td>
	               <td>\${item.job_id}</td>
	               <td>$ \${item.salary.toLocaleString('en-US')}</td>
	               <td>\${item.commission_pct*100}</td>
	               <td>\${managerName}</td>
	               <td>\${item.department_name}</td>
	               <td><img src="./assets/update.png" id="updateIcon" onclick="showModiModal('`+ item.employee_id + `');" style="width:30px; cursor:pointer;"></td>
	               <td><img src="./assets/delete.png" id="deleteIcon" style="width:30px; cursor:pointer;"></td>
	             </tr>`;
		});
		output += `
	         </tbody>
	      </table>
	   `;
		$("#outputEmp").html(output);
	}
   
   function addSelect(datas, id) {
		if (id == 'job_id') {
			$("#" + id).append(`<option value="">직급선택</option>`)

			$.each(datas.datas, function(i, item) {
				$("#" + id).append(`<option>\${item.job_id}</option>`)
			});
		} else if (id == 'department_id') {
			$("#" + id).append(`<option value="">부서선택</option>`)

			$.each(datas.datas, function(i, item) {
				$("#" + id).append(`<option value=\${item.department_id}>\${item.department_name}</option>`)
			})
		} else if (id == 'manager_id') {
			$("#" + id).append(`<option value="">매니저선택</option>`)
			$.each(datas.datas, function(i, item) {
				$("#" + id).append(`<option value=\${item.employee_id}>\${item.last_name} \${item.first_name}</option>`)
			})
		}
	}
   
   	function makeSalInput(job_id) {
		let minSal = 0;
		let maxSal = 0;
		let avgSal = 0;
		$.each(jobData.datas, function(i, item) {
			if (job_id == item.job_id) {
				minSal = item.min_salary;
				maxSal = item.max_salary;
				avgSal = (minSal + maxSal) / 2
			}
		});

		$("#salaryBar").attr("min", minSal);
		$("#salaryBar").attr("max", maxSal);
		$("#salaryBar").attr("value", avgSal);
	}
   
	function showModiModal(empNo) {
		$("#myModal").show();
		$.ajax({
			url: "getEmployee.do",
			type: "GET",
			data: { "empNo": empNo },
			dataType: "json",
			success: function(data) {
				console.log(data);
				bindingData(data);
			},
			error: function(e) {
				console.log(e);
			},
			complete: function() {
			}
		});
	}
   
	function bindingData(data){
		$("#first_name").attr("readonly", true);
		$("#last_name").attr("readonly", true);
	   
		$("#first_name").val(data.employee.first_name);
		$("#last_name").val(data.employee.last_name);
		$("#email").val(data.employee.email);
		$("#phone_number").val(data.employee.phone_number);
	   
		$("#hire_date").val(data.employee.hire_date);
		$("#job_id").val(data.employee.job_id);
	   
		$("#salary").val(data.employee.salary);
	   
		let minSal = 0;
		let maxSal = 0;
		let avgSal = 0;
	      
		$.each(jobData.datas, function(i, item) {
			if(data.employee.job_id == item.job_id) {
				minSal = item.min_salary;
		        maxSal = item.max_salary;
		        avgSal = (minSal+maxSal)/2;
		            
				$("#salaryBar").attr("min", minSal);
				$("#salaryBar").attr("max", maxSal);
				$("#salaryBar").attr("value", data.employee.salary);
			}
		});
		
		$("#commission_pct").val(data.employee.commission_pct * 100);
		$("#manager_id").val(data.employee.manager_id);
		
		$("#department_id").val(data.employee.department_id);
		
		
}
</script>
<style>
#writeIcon {
	cursor: pointer;
	width: 50px;
	height: 50px;
	position: fixed;
	right: 50px;
	top: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Employees with AJAX</h1>

		<div>
			<div id="outputDate" class="genInfo"></div>
			<div id="outputCnt" class="genInfo"></div>
		</div>

		<input type="text" id="findEmpName" class="form-control" placeholder="찾을 사원의 이름을 입력하세요.">

		<!-- 정렬방식 선택 -->
		<div class="form-check">
			<input type="radio" class="form-check-input orderMethod" id="orderEmpNo" name="optradio" value="employee_id" checked>
			사번순(오름차순) 
			<label class="form-check-label" for="orderEmpNo"></label>
		</div>
		<div class="form-check">
			<input type="radio" class="form-check-input orderMethod" id="orderHireDate" name="optradio" value="hire_date">
			입사일순(내림차순)
			<label class="form-check-label  for="orderHireDate"></label>
		</div>
		<div class="form-check">
			<input type="radio" class="form-check-input orderMethod" id="orderSalary" name="optradio" value="salary">
			급여순(내림차순)
			<label class="form-check-label" for="orderSalary"></label>
		</div>
		
		<div id="outputEmp" class="empInfo"></div>
		<img src="./assets/insert.png" id="writeIcon" alt="직원 추가">
	</div>


	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">사원 입력</h4>
					<button type="button" class="btn-close writeBtnClose" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="input-group p-3">
						<span class="input-group-text">성</span> 
						<input type="text" class="form-control" placeholder="성" name="last_name" id="last_name"> 
						<span class="input-group-text">이름</span>
						<input type="text" class="form-control" placeholder="이름" name="first_name" id="first_name">
					</div>
					<div class="input-group p-3">
						<span class="input-group-text">이메일</span> <input type="text"
							class="form-control" placeholder="이메일" name="email" id="email">
					</div>
					<div class="input-group p-3">
						<span class="input-group-text">전화번호</span> <input type="text"
							class="form-control" placeholder="전화번호" name="phone_number"
							id="phone_number">
					</div>
					<div class="input-group p-3">
						<span class="input-group-text">입사일</span> <input type="date"
							class="form-control" name="hire_date" id="hire_date">
					</div>
					<div class="input-group p-3">
						<span class="input-group-text">직급</span> <select
							class="form-select" id="job_id">
						</select>
					</div>
					<div class="input-group p-3">
						<span class="input-group-text">급여</span> <input type="text"
							class="form-control" placeholder="0" name="salary" id="salary">

					</div>
					<div class="input-group p-3">
						<input type="range" "
							class="form-control"
							placeholder="급여" name="salary" id="salaryBar" min="0" max="0">
					</div>

					<div class="input-group p-3">
						<input type="number" class="form-control" placeholder="커미션"
							name="commission_pct" id="commission_pct"> <span
							class="input-group-text">%</span>
					</div>
					<div class="input-group p-3">
						<span class="input-group-text">매니저</span> <select
							class="form-select" id="manager_id">
						</select>
					</div>
					<div class="input-group p-3">
						<span class="input-group-text">부서</span> <select
							class="form-select" id="department_id">
						</select>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-success writeBtn"
						data-bs-dismiss="modal">Save</button>
					<button type="button" class="btn btn-danger writeBtnClose"
						data-bs-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
</body>
</html>