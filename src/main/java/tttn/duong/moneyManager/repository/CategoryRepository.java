package tttn.duong.moneyManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tttn.duong.moneyManager.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    //select * from tbl_catergories where profile_id = ?
    List<CategoryEntity> findByProfileId(Long profileId);

    //select * from tbl_categories where id=?1 and profile_id=?2
    Optional<CategoryEntity> findByIdAndProfileId(Long id, Long profileId);

    //select * from tbl_categories where type=? and profile_id=?
    List<CategoryEntity> findByTypeAndProfileId(String type, Long id);

    //
    Boolean existsByNameAndProfileId(String name,Long profileId);
}
