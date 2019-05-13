package com.lq.muyingmall.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAddressesByUserName(String userName);

    Address findAddressById(long id);
}