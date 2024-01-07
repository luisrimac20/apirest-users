/*
 *
 *  Copyright (c) 2018-2020 Givantha Kalansuriya, This source is a part of
 *   Staxrt - sample application source code.
 *   http://staxrt.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.User;
import com.staxrt.tutorial.repository.UserRepository;
import com.staxrt.tutorial.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type User controller.
 *
 * @author Givantha Kalansuriya
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private IUserService  iUserService;


  /**
   * Get all users list.
   *
   * @return the list
   */
  @GetMapping("/users")
  public List<User> getAllUsers() {
    return iUserService.Listar();
  }

  /**
   * Gets users by id.
   *
   * @param userId the user id
   * @return the users by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId)
      throws ResourceNotFoundException {
    User user =
        iUserService
            .ListarId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
    return ResponseEntity.ok().body(user);
  }

  /**
   * Create user user.
   *
   * @param user the user
   * @return the user
   */
  @PostMapping("/users")
  public User createUser(@Valid @RequestBody User user) {
    return iUserService.creadUser(user);
  }

  /**
   * Update user response entity.
   *
   * @param userId the user id
   * @param userDetails the user details
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(
      @PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
      throws ResourceNotFoundException {

    User user =
        iUserService
            .ListarId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

    user.setEmail(userDetails.getEmail());
    user.setLastName(userDetails.getLastName());
    user.setFirstName(userDetails.getFirstName());
    user.setUpdatedAt(new Date());
    final User updatedUser = iUserService.updateUser(user);
    return ResponseEntity.ok(updatedUser);
  }
  
  
  
  @PatchMapping("/users/{id}")
  public ResponseEntity<User> updateUserName(
      @PathVariable(value = "id") Long userId, @RequestBody User partialUserDetails)
      throws ResourceNotFoundException {

      User user =
          iUserService
              .ListarId(userId)
              .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

      // Actualizar solo el nombre si está presente en los detalles parciales
      
      if (partialUserDetails.getFirstName() != null) {
          user.setFirstName(partialUserDetails.getFirstName());
      }

      // También puedes manejar otros campos si es necesario

      user.setUpdatedAt(new Date());
      final User updatedUser = iUserService.updateUser(user);
      return ResponseEntity.ok(updatedUser);
  }

  /**
   * Delete user map.
   *
   * @param userId the user id
   * @return the map
   * @throws Exception the exception
   */
  @DeleteMapping("/user/{id}")
  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
    User user =
    		iUserService
            .ListarId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

    iUserService.deleteUser(userId);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
  
}
