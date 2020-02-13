<?php
class User
{
    private $_userId;
    private $_userEmail;
    private $_userFirstname;
    private $_userLastname;
    private $_userPassword;
    private $_userType;

    public function __construct($userId, $userEmail, $userFirstname, $userLastname, $userPassword, $userType)
    {
        $this->_userId = $userId;
        $this->_userEmail = $userEmail;
        $this->_userFirstname = $userFirstname;
        $this->_userLastname = $userLastname;
        $this->_userPassword = $userPassword;
        $this->_userType = $userType;
    }

    public function userId()
    {
        return $this->_userId;
    }
    public function userEmail()
    {
        return $this->_userEmail;
    }
    public function userFirstname()
    {
        return $this->_userFirstname;
    }
    public function userLastname()
    {
        return $this->_userLastname;
    }
    public function userPassword()
    {
        return $this->_userPassword;
    }
    public function userType()
    {
        return $this->_userType;
    }

}
?>