package csc340project.example.springio.GroupMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer> {
    @Query(value = "delete from groupmembers where listing_id = ?1 and user_id = ?2", nativeQuery = true)
    public void removeGroupMember(int groupId, int memberId);

    @Query(value = "select * from groupmembers where listing_id = ?2 and user_id = ?1", nativeQuery = true)
    public GroupMember getGroupMemberById(int memberId, int groupId);
}
