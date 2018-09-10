package test;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fq.dao.EmployeeMapper;
import com.fq.entify.Employee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestDataSource {
    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    private EmployeeMapper mapper = ioc.getBean("employeeMapper",EmployeeMapper.class);

   /*
        代码生成器
    */

   @Test
   public void testGenerator(){
       /**
        * 代码生成    示例代码
        */
           //1. 全局配置
           GlobalConfig config = new GlobalConfig();
           config.setActiveRecord(true) // 是否支持AR模式
                   .setAuthor("冯庆") // 作者
                   .setOutputDir("A:\\Code\\intelliJ\\myBatisPlus\\src\\main\\java") // 生成路径
                   .setFileOverride(true)  // 文件覆盖
                   .setIdType(IdType.AUTO) // 主键策略
                   .setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I
                   // IEmployeeService
                   .setBaseResultMap(true)
                   .setBaseColumnList(true);

           //2. 数据源配置
           DataSourceConfig dsConfig  = new DataSourceConfig();
           dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
                   .setDriverName("com.mysql.jdbc.Driver")
                   .setUrl("jdbc:mysql://localhost:3306/mp")
                   .setUsername("root")
                   .setPassword("root");

           //3. 策略配置
           StrategyConfig stConfig = new StrategyConfig();
           stConfig.setCapitalMode(true) //全局大写命名
                   .setDbColumnUnderline(true)  // 指定表名 字段名是否使用下划线
                   .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                   .setTablePrefix("tbl_")
                   .setInclude("tbl_employee");  // 生成的表

           //4. 包名策略配置
           PackageConfig pkConfig = new PackageConfig();
           pkConfig.setParent("com.atguigu.mp")
                   .setMapper("mapper")
                   .setService("service")
                   .setController("controller")
                   .setEntity("beans")
                   .setXml("mapper");

           //5. 整合配置
           AutoGenerator ag = new AutoGenerator();

           ag.setGlobalConfig(config)
                   .setDataSource(dsConfig)
                   .setStrategy(stConfig)
                   .setPackageInfo(pkConfig);

           //6. 执行
           ag.execute();
   }

   /*
   * 插件
   *
   * */

   @Test
   public void testPlugin(){
       Employee employee = new Employee();
       Page<Employee> page = new Page<>(1, 5);
       Page<Employee> employeePage = employee.selectPage(page, null);
       System.out.println(employeePage.getRecords());
       System.out.println(page.getTotal());
       System.out.println(page.hasPrevious());
       System.out.println(page.hasNext());
       System.out.println(page.getPages());
       System.out.println(page.hasNext());
   }

   @Test
   public void testExplanInterceptor(){
       Integer deleteCount = mapper.delete(null);
       System.out.println(deleteCount);
   }

  /*
        AR模式
    */

    @Test
    public void testAr(){
        Employee employee = new Employee("小花", "fq@qq.com", 0, 16);
        boolean insert = employee.insert();


        System.out.println(insert);
    }

    @Test
     public void testInsert(){
        Employee employee = new Employee("小官", "fq@qq.com", 0, 56);

        Integer result = mapper.insert(employee);
        System.out.println("返回主键"+employee.getId());
        System.out.println(result);
    }

    @Test
    public void test(){
        Employee employee = new Employee("小龙", "fq@qq.com", 1, 16);
        employee.setId(4);
        Integer integer = mapper.updateById(employee);
        Employee employee1 = mapper.selectById(3);
        System.out.println(employee1);
    }


    /*public void delete(){
       // Integer integer = mapper.deleteById(3);
        //System.out.println(integer);
    }*/


    //使用wrapper查询
    @Test
    public void test1(){
        List<Employee> list = mapper.selectPage(new Page<Employee>(2, 2), new EntityWrapper<Employee>()
                .between("age", 18, 50)
                .eq("gender", 1)
                .eq("last_name", "小龙")
        );

        List<Employee> list1 = mapper.selectList(new EntityWrapper<Employee>()
                .eq("last_name", "小龙")
                .between("age", 18, 50)
                .ne("age",25)
                .or()
                .eq("last_name", "Jerry")
        );

        System.out.println(list);
        System.out.println(list1);
    }

    /*public static void main(String[] args) {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        Employee employee = ioc.getBean("employee", Employee.class);
        employee.test();
        System.out.println(ioc);
    }*/
}
