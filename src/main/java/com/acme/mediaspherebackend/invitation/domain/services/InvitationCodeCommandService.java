package com.acme.mediaspherebackend.invitation.domain.services;

import com.acme.mediaspherebackend.invitation.domain.model.aggregates.InvitationCode;
import com.acme.mediaspherebackend.invitation.domain.model.commands.CreateInvitationCodeCommand;

import java.util.Optional;

public interface InvitationCodeCommandService {
    Optional<InvitationCode> handle(CreateInvitationCodeCommand command);
}
