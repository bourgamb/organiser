package com.bourg.organiser.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Created by Bourg, Ambrose on 22/01/2022
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("The organiser");
    }

}
