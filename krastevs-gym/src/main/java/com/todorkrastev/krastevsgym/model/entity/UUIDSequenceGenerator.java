package com.todorkrastev.krastevsgym.model.entity;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.util.EnumSet;
import java.util.UUID;

public class UUIDSequenceGenerator implements BeforeExecutionGenerator {
    @Override
    public Object generate(
            SharedSessionContractImplementor sharedSessionContractImplementor,
            Object o,
            Object o1,
            EventType eventType) {
        return UUID.randomUUID();
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }
}
