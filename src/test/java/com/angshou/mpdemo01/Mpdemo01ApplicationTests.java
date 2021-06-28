package com.angshou.mpdemo01;

import com.angshou.mpdemo01.entiy.User;
import com.angshou.mpdemo01.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class Mpdemo01ApplicationTests {

	@Autowired
	private UserMapper userMapper;


	// 查询 user表 所有数据
	@Test
	public void findAll() {
		List<User> users = userMapper.selectList(null);
		System.out.println(users);
	}


	// 添加 user表
	@Test
	public void addUser() {
		User user = new User();
		user.setName("hahha");
		user.setAge(15);
		user.setEmail("luck@163.com");

		int insert = userMapper.insert(user);
		System.out.println("insert: "+insert);
	}


	// 修改 user表
	@Test
	public void updateUser() {
		User user = new User();
		user.setId(2L);
		user.setAge(300);

		int update = userMapper.updateById(user);
		System.out.println("insert: "+update);
	}


	// 测试 乐观锁插件（成功）
	@Test
	public void testOptimisticLocker() {
		//查询
		User user = userMapper.selectById(1409350820381671426L);
		//修改数据
		user.setName("Helen Yao");
		user.setEmail("helen@qq.com");
		//执行更新
		userMapper.updateById(user);
	}


	 // 测试乐观锁插件 失败
	@Test
	public void testOptimisticLockerFail() {
		//查询
		User user = userMapper.selectById(1409350820381671426L);
		//修改数据
		user.setName("Helen Yao1");
		user.setEmail("helen@qq.com1");
		//模拟取出数据后，数据库中version实际数据比取出的值大，即已被其它线程修改并更新了version
		user.setVersion(user.getVersion() - 1);
		//执行更新
		userMapper.updateById(user);
	}


	// 物理删除 user表
	@Test
	public void deleteUser() {
		int delete = userMapper.deleteById(1409343559076679682L);
		System.out.println(delete);
	}

	// 逻辑删除 user表
	@Test
	public void deleteUser2() {
		int delete = userMapper.deleteById(1409338898307416065L);
		System.out.println(delete);
	}


	// 多个 id 批量查询
	@Test
	public void testSelectBatchIds(){
		List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
		users.forEach(System.out::println);
	}


	// 分页查询
	@Test
	public void testSelectPage() {
		// 创建page对象，参数：当前页、每页显示记录数
		Page<User> page = new Page<>(1,3);
		// 调用 mp 分页查询的方法
		// 调用 mp 分页查询过程中，底层封装
		// 把分页所有数据封装到 page 对象里面
		userMapper.selectPage(page, null);

		// 打印
		page.getRecords().forEach(System.out::println);


		System.out.println(page.getCurrent()); // 当前页
		System.out.println(page.getPages());   // 每页数据list 集合
		System.out.println(page.getSize());    // 每页显示记录数
		System.out.println(page.getTotal());   // 总记录数
		System.out.println(page.hasNext());    // 下一页
		System.out.println(page.hasPrevious());// 上一页
	}

}
