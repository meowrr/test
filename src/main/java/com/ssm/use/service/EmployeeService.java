package com.ssm.use.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.use.bean.Employee;
import com.ssm.use.bean.EmployeeExample;
import com.ssm.use.bean.EmployeeExample.Criteria;
import com.ssm.use.dao.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;

	/*
	 * 查询所有员工
	 * 
	 * @return
	 */
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExampleWithDept(null);
	}

	/*
	 * 员工保存方法
	 */
	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
	}

	// 检验用户名是否可用
	// return true代表当前姓名可用 false 不可用
	public boolean checkUser(String empName) {
		// TODO Auto-generated method stub

		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}

	/*
	 * 按照员工id查询员工
	 */
	public Employee getEmp(Integer id) {
		// TODO Auto-generated method stub
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	/*
	 * 员工更新
	 */
	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	/*
	 * 员工删除
	 */
	public void deleteEmp(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
	}

	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		//delete from xxx where emp_id in(1,2,3);
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}

}
