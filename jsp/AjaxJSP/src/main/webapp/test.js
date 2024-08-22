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