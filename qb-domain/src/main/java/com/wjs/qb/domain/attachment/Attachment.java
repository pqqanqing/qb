package com.wjs.qb.domain.attachment;

import com.wjs.common.base.base.BaseEntity;
import com.wjs.qb.api.dto.AttachmentDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Attachment extends BaseEntity {

    private Long ownerId;
    private String ownerType;
    private String category;
    private String path;

    public Attachment() {
    }

    public Attachment(AttachmentDTO attachmentDTO) {
        this.category = attachmentDTO.getCategory();
        this.path = attachmentDTO.getPath();
        this.ownerType = attachmentDTO.getOwnerType();
    }

    public Attachment(String path) {
        this();
        this.path = path;
    }
}
