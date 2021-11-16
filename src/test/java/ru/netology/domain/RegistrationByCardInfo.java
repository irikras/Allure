package ru.netology.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationByCardInfo {
    private final String city;
    private final String name;
    private final String phoneNumber;

}
