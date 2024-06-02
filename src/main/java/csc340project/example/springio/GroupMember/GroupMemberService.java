package csc340project.example.springio.GroupMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class GroupMemberService {
    @Autowired
    GroupMemberRepository groupMemberRepository;

    public void addNewMember(GroupMember groupMember) {
        groupMemberRepository.save(groupMember);
    }

    public boolean userExistsInGroup(int userId, int groupId) {
        Example<GroupMember> groupMemberExample = Example.of(getMemberById(userId, groupId));
        return groupMemberRepository.exists(groupMemberExample);
    }

    public void removeMember(int memberId, int groupId) {
        groupMemberRepository.removeGroupMember(groupId, memberId);
    }

    public GroupMember getMemberById(int memberId, int groupId) {
        return groupMemberRepository.getGroupMemberById(memberId, groupId);
    }
}
