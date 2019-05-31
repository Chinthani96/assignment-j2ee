package lk.nsbm.service.custom;

import lk.nsbm.dto.AnnouncementDTO;
import lk.nsbm.dto.UserDTO;
import lk.nsbm.service.SuperService;

import java.util.List;

public interface AnnouncementService extends SuperService {
    void loadAnnouncements(UserDTO userDTO);

    List<AnnouncementDTO> getAnnouncementListOfUser(UserDTO userDTO);

}
