package Axis.Axis_Spring.data.repository;
import Axis.Axis_Spring.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface   ProductRepository extends JpaRepository <ProductEntity, String> {

}

//JpaRepository<레포지토리가 사용할 엔티티, primary key의 id값 데이터 타입>
//JpaRepository를 사용하게 되면 @Repository을 사용할 필요가 없다, 코드를 작성하지 않아도  기본적인 CRUD메서드를 제공한다.
//이렇게 작성하면 ..쿼리문이 실제로..insert into...식으로 실행이 된다.
