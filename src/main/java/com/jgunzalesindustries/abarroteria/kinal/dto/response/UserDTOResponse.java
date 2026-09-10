/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.jgunzalesindustries.abarroteria.kinal.dto.response;

/**
 *
 * @author angel
 */
public class UserDTOResponse {
        private String idUser;
    private String name;
    private String lastName;
    private String mail;
    private int idRol;

        public UserDTOResponse(String idUser, String name, String lastName, String mail, int idRol) {
            this.idUser = idUser;
            this.name = name;
            this.lastName = lastName;
            this.mail = mail;
            this.idRol = idRol;
        }

    }

