<?php
class Joueur{

    private $_ID;
    private $_Nom;
    private $_Prenom;
    private $_ClassATP;
    private $_Nationnalite;

    public function __construct($ID, $Nom, $Prenom, $ClasseATP, $Nationnalite)
    {
        $this->_ID = $ID;
        $this->_Nom = $Nom;
        $this->_Prenom = $Prenom;
        $this->_ClassATP = $ClasseATP;
        $this->_Nationnalite = $Nationnalite;
    }

    public function ID()
    {
        return $this->_ID;
    }
    public function Nom()
    {
        return $this->_Nom;
    }
    public function Prenom()
    {
        return $this->_Prenom;
    }
    public function ClassATP()
    {
        return $this->_ClassATP;
    }
    public function Nationnalite()
    {
        return $this->_Nationnalite;
    }

  
}