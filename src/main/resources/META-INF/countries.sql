INSERT INTO users (user_name, user_pass, birth, driverLicenseNumber, email, firstName, lastName, gender, phone, status) 
VALUES ('TestUser', '$10$W6r96gcBrkhwVzzSTAFQju.d4seOMV/wvoDeuiLp2.4XfnQJ7Z46C', '2019-05-13', '1234567890', 'test@test.test', 'Test', 'Test', 'male', 'Active');
INSERT INTO users (user_name, user_pass, birth, driverLicenseNumber, email, firstName, lastName, gender, phone, status) 
VALUES ('TestAdmin', '$10$24ncNkwzcZRcKiW3vu/Av.tgedPlUOH7quyqrQAAxdB5IAfg.8lU2', '2019-05-13', '0987654321', 'test2@test2.test', 'Test2', 'Test2', 'male', 'Active');
INSERT INTO roles (role_name) VALUES ('user');
INSERT INTO roles (role_name) VALUES ('admin');
INSERT INTO user_roles (user_name, role_name) VALUES ('user', 'admin');
INSERT INTO user_roles (user_name, role_name) VALUES ('admin', 'admin');

INSERT INTO `country` (`country`) VALUES ('Afghanistan');
INSERT INTO `country` (`country`) VALUES ('Albania');
INSERT INTO `country` (`country`) VALUES ('Algeria');
INSERT INTO `country` (`country`) VALUES ('American Samoa');
INSERT INTO `country` (`country`) VALUES ('Andorra');
INSERT INTO `country` (`country`) VALUES ('Angola');
INSERT INTO `country` (`country`) VALUES ('Anguilla');
INSERT INTO `country` (`country`) VALUES ('Antarctica');
INSERT INTO `country` (`country`) VALUES ('Antigua and Barbuda');
INSERT INTO `country` (`country`) VALUES ('Argentina');
INSERT INTO `country` (`country`) VALUES ('Armenia');
INSERT INTO `country` (`country`) VALUES ('Aruba');
INSERT INTO `country` (`country`) VALUES ('Australia');
INSERT INTO `country` (`country`) VALUES ('Austria');
INSERT INTO `country` (`country`) VALUES ('Azerbaijan');
INSERT INTO `country` (`country`) VALUES ('Bahamas');
INSERT INTO `country` (`country`) VALUES ('Bahrain');
INSERT INTO `country` (`country`) VALUES ('Bangladesh');
INSERT INTO `country` (`country`) VALUES ('Barbados');
INSERT INTO `country` (`country`) VALUES ('Belarus');
INSERT INTO `country` (`country`) VALUES ('Belgium');
INSERT INTO `country` (`country`) VALUES ('Belize');
INSERT INTO `country` (`country`) VALUES ('Benin');
INSERT INTO `country` (`country`) VALUES ('Bermuda');
INSERT INTO `country` (`country`) VALUES ('Bhutan');
INSERT INTO `country` (`country`) VALUES ('Bolivia');
INSERT INTO `country` (`country`) VALUES ('Bosnia and Herzegovina');
INSERT INTO `country` (`country`) VALUES ('Botswana');
INSERT INTO `country` (`country`) VALUES ('Bouvet Island');
INSERT INTO `country` (`country`) VALUES ('Brazil');
INSERT INTO `country` (`country`) VALUES ('British Indian Ocean Territory');
INSERT INTO `country` (`country`) VALUES ('Brunei Darussalam');
INSERT INTO `country` (`country`) VALUES ('Bulgaria');
INSERT INTO `country` (`country`) VALUES ('Burkina Faso');
INSERT INTO `country` (`country`) VALUES ('Burundi');
INSERT INTO `country` (`country`) VALUES ('Cambodia');
INSERT INTO `country` (`country`) VALUES ('Cameroon');
INSERT INTO `country` (`country`) VALUES ('Canada');
INSERT INTO `country` (`country`) VALUES ('Cape Verde');
INSERT INTO `country` (`country`) VALUES ('Cayman Islands');
INSERT INTO `country` (`country`) VALUES ('Central African Republic');
INSERT INTO `country` (`country`) VALUES ('Chad');
INSERT INTO `country` (`country`) VALUES ('Chile');
INSERT INTO `country` (`country`) VALUES ('China');
INSERT INTO `country` (`country`) VALUES ('Christmas Island');
INSERT INTO `country` (`country`) VALUES ('Cocos (Keeling) Islands');
INSERT INTO `country` (`country`) VALUES ('Colombia');
INSERT INTO `country` (`country`) VALUES ('Comoros');
INSERT INTO `country` (`country`) VALUES ('Congo');
INSERT INTO `country` (`country`) VALUES ('Cook Islands');
INSERT INTO `country` (`country`) VALUES ('Costa Rica');
INSERT INTO `country` (`country`) VALUES ('Croatia (Hrvatska)');
INSERT INTO `country` (`country`) VALUES ('Cuba');
INSERT INTO `country` (`country`) VALUES ('Cyprus');
INSERT INTO `country` (`country`) VALUES ('Czech Republic');
INSERT INTO `country` (`country`) VALUES ('Denmark');
INSERT INTO `country` (`country`) VALUES ('Djibouti');
INSERT INTO `country` (`country`) VALUES ('Dominica');
INSERT INTO `country` (`country`) VALUES ('Dominican Republic');
INSERT INTO `country` (`country`) VALUES ('East Timor');
INSERT INTO `country` (`country`) VALUES ('Ecuador');
INSERT INTO `country` (`country`) VALUES ('Egypt');
INSERT INTO `country` (`country`) VALUES ('El Salvador');
INSERT INTO `country` (`country`) VALUES ('Equatorial Guinea');
INSERT INTO `country` (`country`) VALUES ('Eritrea');
INSERT INTO `country` (`country`) VALUES ('Estonia');
INSERT INTO `country` (`country`) VALUES ('Ethiopia');
INSERT INTO `country` (`country`) VALUES ('Falkland Islands (Malvinas)');
INSERT INTO `country` (`country`) VALUES ('Faroe Islands');
INSERT INTO `country` (`country`) VALUES ('Fiji');
INSERT INTO `country` (`country`) VALUES ('Finland');
INSERT INTO `country` (`country`) VALUES ('France');
INSERT INTO `country` (`country`) VALUES ('France, Metropolitan');
INSERT INTO `country` (`country`) VALUES ('French Guiana');
INSERT INTO `country` (`country`) VALUES ('French Polynesia');
INSERT INTO `country` (`country`) VALUES ('French Southern Territories');
INSERT INTO `country` (`country`) VALUES ('Gabon');
INSERT INTO `country` (`country`) VALUES ('Gambia');
INSERT INTO `country` (`country`) VALUES ('Georgia');
INSERT INTO `country` (`country`) VALUES ('Germany');
INSERT INTO `country` (`country`) VALUES ('Ghana');
INSERT INTO `country` (`country`) VALUES ('Gibraltar');
INSERT INTO `country` (`country`) VALUES ('Guernsey');
INSERT INTO `country` (`country`) VALUES ('Greece');
INSERT INTO `country` (`country`) VALUES ('Greenland');
INSERT INTO `country` (`country`) VALUES ('Grenada');
INSERT INTO `country` (`country`) VALUES ('Guadeloupe');
INSERT INTO `country` (`country`) VALUES ('Guam');
INSERT INTO `country` (`country`) VALUES ('Guatemala');
INSERT INTO `country` (`country`) VALUES ('Guinea');
INSERT INTO `country` (`country`) VALUES ('Guinea-Bissau');
INSERT INTO `country` (`country`) VALUES ('Guyana');
INSERT INTO `country` (`country`) VALUES ('Haiti');
INSERT INTO `country` (`country`) VALUES ('Heard and Mc Donald Islands');
INSERT INTO `country` (`country`) VALUES ('Honduras');
INSERT INTO `country` (`country`) VALUES ('Hong Kong');
INSERT INTO `country` (`country`) VALUES ('Hungary');
INSERT INTO `country` (`country`) VALUES ('Iceland');
INSERT INTO `country` (`country`) VALUES ('India');
INSERT INTO `country` (`country`) VALUES ('Isle of Man');
INSERT INTO `country` (`country`) VALUES ('Indonesia');
INSERT INTO `country` (`country`) VALUES ('Iran (Islamic Republic of)');
INSERT INTO `country` (`country`) VALUES ('Iraq');
INSERT INTO `country` (`country`) VALUES ('Ireland');
INSERT INTO `country` (`country`) VALUES ('Israel');
INSERT INTO `country` (`country`) VALUES ('Italy');
INSERT INTO `country` (`country`) VALUES ('Ivory Coast');
INSERT INTO `country` (`country`) VALUES ('Jersey');
INSERT INTO `country` (`country`) VALUES ('Jamaica');
INSERT INTO `country` (`country`) VALUES ('Japan');
INSERT INTO `country` (`country`) VALUES ('Jordan');
INSERT INTO `country` (`country`) VALUES ('Kazakhstan');
INSERT INTO `country` (`country`) VALUES ('Kenya');
INSERT INTO `country` (`country`) VALUES ('Kiribati');
INSERT INTO `country` (`country`) VALUES ('Korea, Democratic People''s Republic of');
INSERT INTO `country` (`country`) VALUES ('Korea, Republic of');
INSERT INTO `country` (`country`) VALUES ('Kosovo');
INSERT INTO `country` (`country`) VALUES ('Kuwait');
INSERT INTO `country` (`country`) VALUES ('Kyrgyzstan');
INSERT INTO `country` (`country`) VALUES ('Lao People''s Democratic Republic');
INSERT INTO `country` (`country`) VALUES ('Latvia');
INSERT INTO `country` (`country`) VALUES ('Lebanon');
INSERT INTO `country` (`country`) VALUES ('Lesotho');
INSERT INTO `country` (`country`) VALUES ('Liberia');
INSERT INTO `country` (`country`) VALUES ('Libyan Arab Jamahiriya');
INSERT INTO `country` (`country`) VALUES ('Liechtenstein');
INSERT INTO `country` (`country`) VALUES ('Lithuania');
INSERT INTO `country` (`country`) VALUES ('Luxembourg');
INSERT INTO `country` (`country`) VALUES ('Macau');
INSERT INTO `country` (`country`) VALUES ('Macedonia');
INSERT INTO `country` (`country`) VALUES ('Madagascar');
INSERT INTO `country` (`country`) VALUES ('Malawi');
INSERT INTO `country` (`country`) VALUES ('Malaysia');
INSERT INTO `country` (`country`) VALUES ('Maldives');
INSERT INTO `country` (`country`) VALUES ('Mali');
INSERT INTO `country` (`country`) VALUES ('Malta');
INSERT INTO `country` (`country`) VALUES ('Marshall Islands');
INSERT INTO `country` (`country`) VALUES ('Martinique');
INSERT INTO `country` (`country`) VALUES ('Mauritania');
INSERT INTO `country` (`country`) VALUES ('Mauritius');
INSERT INTO `country` (`country`) VALUES ('Mayotte');
INSERT INTO `country` (`country`) VALUES ('Mexico');
INSERT INTO `country` (`country`) VALUES ('Micronesia, Federated States of');
INSERT INTO `country` (`country`) VALUES ('Moldova, Republic of');
INSERT INTO `country` (`country`) VALUES ('Monaco');
INSERT INTO `country` (`country`) VALUES ('Mongolia');
INSERT INTO `country` (`country`) VALUES ('Montenegro');
INSERT INTO `country` (`country`) VALUES ('Montserrat');
INSERT INTO `country` (`country`) VALUES ('Morocco');
INSERT INTO `country` (`country`) VALUES ('Mozambique');
INSERT INTO `country` (`country`) VALUES ('Myanmar');
INSERT INTO `country` (`country`) VALUES ('Namibia');
INSERT INTO `country` (`country`) VALUES ('Nauru');
INSERT INTO `country` (`country`) VALUES ('Nepal');
INSERT INTO `country` (`country`) VALUES ('Netherlands');
INSERT INTO `country` (`country`) VALUES ('Netherlands Antilles');
INSERT INTO `country` (`country`) VALUES ('New Caledonia');
INSERT INTO `country` (`country`) VALUES ('New Zealand');
INSERT INTO `country` (`country`) VALUES ('Nicaragua');
INSERT INTO `country` (`country`) VALUES ('Niger');
INSERT INTO `country` (`country`) VALUES ('Nigeria');
INSERT INTO `country` (`country`) VALUES ('Niue');
INSERT INTO `country` (`country`) VALUES ('Norfolk Island');
INSERT INTO `country` (`country`) VALUES ('Northern Mariana Islands');
INSERT INTO `country` (`country`) VALUES ('Norway');
INSERT INTO `country` (`country`) VALUES ('Oman');
INSERT INTO `country` (`country`) VALUES ('Pakistan');
INSERT INTO `country` (`country`) VALUES ('Palau');
INSERT INTO `country` (`country`) VALUES ('Palestine');
INSERT INTO `country` (`country`) VALUES ('Panama');
INSERT INTO `country` (`country`) VALUES ('Papua New Guinea');
INSERT INTO `country` (`country`) VALUES ('Paraguay');
INSERT INTO `country` (`country`) VALUES ('Peru');
INSERT INTO `country` (`country`) VALUES ('Philippines');
INSERT INTO `country` (`country`) VALUES ('Pitcairn');
INSERT INTO `country` (`country`) VALUES ('Poland');
INSERT INTO `country` (`country`) VALUES ('Portugal');
INSERT INTO `country` (`country`) VALUES ('Puerto Rico');
INSERT INTO `country` (`country`) VALUES ('Qatar');
INSERT INTO `country` (`country`) VALUES ('Reunion');
INSERT INTO `country` (`country`) VALUES ('Romania');
INSERT INTO `country` (`country`) VALUES ('Russian Federation');
INSERT INTO `country` (`country`) VALUES ('Rwanda');
INSERT INTO `country` (`country`) VALUES ('Saint Kitts and Nevis');
INSERT INTO `country` (`country`) VALUES ('Saint Lucia');
INSERT INTO `country` (`country`) VALUES ('Saint Vincent and the Grenadines');
INSERT INTO `country` (`country`) VALUES ('Samoa');
INSERT INTO `country` (`country`) VALUES ('San Marino');
INSERT INTO `country` (`country`) VALUES ('Sao Tome and Principe');
INSERT INTO `country` (`country`) VALUES ('Saudi Arabia');
INSERT INTO `country` (`country`) VALUES ('Senegal');
INSERT INTO `country` (`country`) VALUES ('Serbia');
INSERT INTO `country` (`country`) VALUES ('Seychelles');
INSERT INTO `country` (`country`) VALUES ('Sierra Leone');
INSERT INTO `country` (`country`) VALUES ('Singapore');
INSERT INTO `country` (`country`) VALUES ('Slovakia');
INSERT INTO `country` (`country`) VALUES ('Slovenia');
INSERT INTO `country` (`country`) VALUES ('Solomon Islands');
INSERT INTO `country` (`country`) VALUES ('Somalia');
INSERT INTO `country` (`country`) VALUES ('South Africa');
INSERT INTO `country` (`country`) VALUES ('South Georgia South Sandwich Islands');
INSERT INTO `country` (`country`) VALUES ('South Sudan');
INSERT INTO `country` (`country`) VALUES ('Spain');
INSERT INTO `country` (`country`) VALUES ('Sri Lanka');
INSERT INTO `country` (`country`) VALUES ('St. Helena');
INSERT INTO `country` (`country`) VALUES ('St. Pierre and Miquelon');
INSERT INTO `country` (`country`) VALUES ('Sudan');
INSERT INTO `country` (`country`) VALUES ('Suriname');
INSERT INTO `country` (`country`) VALUES ('Svalbard and Jan Mayen Islands');
INSERT INTO `country` (`country`) VALUES ('Swaziland');
INSERT INTO `country` (`country`) VALUES ('Sweden');
INSERT INTO `country` (`country`) VALUES ('Switzerland');
INSERT INTO `country` (`country`) VALUES ('Syrian Arab Republic');
INSERT INTO `country` (`country`) VALUES ('Taiwan');
INSERT INTO `country` (`country`) VALUES ('Tajikistan');
INSERT INTO `country` (`country`) VALUES ('Tanzania, United Republic of');
INSERT INTO `country` (`country`) VALUES ('Thailand');
INSERT INTO `country` (`country`) VALUES ('Togo');
INSERT INTO `country` (`country`) VALUES ('Tokelau');
INSERT INTO `country` (`country`) VALUES ('Tonga');
INSERT INTO `country` (`country`) VALUES ('Trinidad and Tobago');
INSERT INTO `country` (`country`) VALUES ('Tunisia');
INSERT INTO `country` (`country`) VALUES ('Turkey');
INSERT INTO `country` (`country`) VALUES ('Turkmenistan');
INSERT INTO `country` (`country`) VALUES ('Turks and Caicos Islands');
INSERT INTO `country` (`country`) VALUES ('Tuvalu');
INSERT INTO `country` (`country`) VALUES ('Uganda');
INSERT INTO `country` (`country`) VALUES ('Ukraine');
INSERT INTO `country` (`country`) VALUES ('United Arab Emirates');
INSERT INTO `country` (`country`) VALUES ('United Kingdom');
INSERT INTO `country` (`country`) VALUES ('United States');
INSERT INTO `country` (`country`) VALUES ('United States minor outlying islands');
INSERT INTO `country` (`country`) VALUES ('Uruguay');
INSERT INTO `country` (`country`) VALUES ('Uzbekistan');
INSERT INTO `country` (`country`) VALUES ('Vanuatu');
INSERT INTO `country` (`country`) VALUES ('Vatican City State');
INSERT INTO `country` (`country`) VALUES ('Venezuela');
INSERT INTO `country` (`country`) VALUES ('Vietnam');
INSERT INTO `country` (`country`) VALUES ('Virgin Islands (British)');
INSERT INTO `country` (`country`) VALUES ('Virgin Islands (U.S.)');
INSERT INTO `country` (`country`) VALUES ('Wallis and Futuna Islands');
INSERT INTO `country` (`country`) VALUES ('Western Sahara');
INSERT INTO `country` (`country`) VALUES ('Yemen');
INSERT INTO `country` (`country`) VALUES ('Zaire');
INSERT INTO `country` (`country`) VALUES ('Zambia');
INSERT INTO `country` (`country`) VALUES ('Zimbabwe');