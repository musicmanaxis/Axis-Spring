package Axis.Axis_Spring.data.repository;

public interface productRepository extends JpaRepository<ProductEntiy, String>{
    //JpaRepository<레포지토리가 사용할 엔터티, id값이 스트링이므로.>
}
