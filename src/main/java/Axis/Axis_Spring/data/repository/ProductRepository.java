package Axis.Axis_Spring.data.repository;


import Axis.Axis_Spring.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}

//JpaRepository<레포지토리가 사용할 엔티티, id값의 데이터 타입>