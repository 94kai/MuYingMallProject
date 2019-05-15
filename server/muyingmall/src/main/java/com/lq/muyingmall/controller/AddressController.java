package com.lq.muyingmall.controller;

import com.lq.muyingmall.domain.Address;
import com.lq.muyingmall.domain.AddressRepository;
import com.lq.muyingmall.domain.BaseResponse;
import com.lq.muyingmall.utils.TextUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;


    @RequestMapping(value = "/queryAllAddressByUserName", method = {RequestMethod.GET})
    public BaseResponse<List<Address>> queryAllAddressByUserName(String userName) {
        List<Address> addressesByUserName = addressRepository.findAddressesByUserName(userName);
        return new BaseResponse<>(0, addressesByUserName);
    }

    @RequestMapping(value = "/addAddress", method = {RequestMethod.GET})
    public BaseResponse addAddress(String consignee, String address, String phoneNumber, String userName) {
        Address addressBean = new Address();
        addressBean.setAddress(address);
        addressBean.setConsignee(consignee);
        addressBean.setPhoneNumber(phoneNumber);
        addressBean.setUserName(userName);
        try {
            addressRepository.save(addressBean);
        } catch (Exception e) {
            return new BaseResponse<>(-1, "error");
        }
        return new BaseResponse<>(0, "success");
    }

    @RequestMapping(value = "/updateAddress", method = {RequestMethod.GET})
    public BaseResponse updateAddress(String consignee, String address, String phoneNumber, long addressId) {
        Address addressById = addressRepository.findAddressById(addressId);
        if (addressById == null) {
            return new BaseResponse<>(-1, "不存在该地址");
        }
        String userName = addressById.getUserName();
        if (TextUtils.isEmpty(consignee)) {
            consignee = addressById.getConsignee();
        }
        if (TextUtils.isEmpty(address)) {
            address = addressById.getAddress();
        }
        if (TextUtils.isEmpty(phoneNumber)) {
            phoneNumber = addressById.getPhoneNumber();
        }
        addressRepository.delete(addressById);
        Address addressBean = new Address();
        addressBean.setAddress(address);
        addressBean.setConsignee(consignee);
        addressBean.setPhoneNumber(phoneNumber);
        addressBean.setUserName(userName);
        try {
            addressRepository.save(addressBean);
        } catch (Exception e) {
            return new BaseResponse<>(-1, "error");
        }
        return new BaseResponse<>(0, "success");
    }
    @RequestMapping(value = "/deleteAddress", method = {RequestMethod.GET})
    public BaseResponse deleteAddress(long addressId) {
        Address addressById = addressRepository.findAddressById(addressId);
        if (addressById == null) {
            return new BaseResponse<>(-1, "不存在该地址");
        }
        try {
            addressRepository.delete(addressById);
        } catch (Exception e) {
            return new BaseResponse<>(-1, "error");
        }
        return new BaseResponse<>(0, "success");
    }

}
