package com.acme.mediaspherebackend.invitation.application.internal.commandservices;

import com.acme.mediaspherebackend.invitation.domain.model.aggregates.InvitationCode;
import com.acme.mediaspherebackend.invitation.domain.model.commands.CreateInvitationCodeCommand;
import com.acme.mediaspherebackend.invitation.domain.services.InvitationCodeCommandService;
import com.acme.mediaspherebackend.invitation.infraestructure.persistence.jpa.repositories.InvitationCodeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvitationCodeCommandServiceImpl implements InvitationCodeCommandService {
    private final InvitationCodeRepository invitationCodeRepository;

    public InvitationCodeCommandServiceImpl(InvitationCodeRepository invitationCodeRepository) {
        this.invitationCodeRepository = invitationCodeRepository;
    }

    @Override
    public Optional<InvitationCode> handle(CreateInvitationCodeCommand command) {
        String newUniqueInvitationCode = generateUniqueInvitationCode();

        var invitationCode = new InvitationCode(command, newUniqueInvitationCode);

        this.invitationCodeRepository.save(invitationCode);

        return Optional.of(invitationCode);
    }


    // Generate Unique Code for Invitation
    private String generateUniqueInvitationCode() {
        String code = "";
        do {
            code = generateInvitationCode();
        } while (this.invitationCodeRepository.existsByCode(code));

        return code;
    }

    // Generate Code for Invitation
    private String generateInvitationCode() {
        int newCode = (int) (Math.random() * 90000000) + 10000000;
        return Integer.toString(newCode);
    }
}
