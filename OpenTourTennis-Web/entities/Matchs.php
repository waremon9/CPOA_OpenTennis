<?php
class Matchs{

    private $_idM;
    private $_noCourt;
    private $_CategorieM;
    private $_DateM;
    private $_HeureDebutM;
    private $_rangTournoi;

    public function __construct($idJ, $noCourt, $CategorieM, $DateM, $HeureDebutM, $rangTournoi)
    {
        $this->_idJ = $idJ;
        $this->_noCourt = $noCourt;
        $this->_CategorieM = $CategorieM;
        $this->_DateM = $DateM;
        $this->_HeureDebutM = $HeureDebutM;
        $this->_rangTournoi = $rangTournoi;
 
    }

    public function idJ()
    {
        return $this->_idJ;
    }
    public function noCourt()
    {
        return $this->_noCourt;
    }
    public function CategorieM()
    {
        return $this->_CategorieM;
    }
    public function DateM()
    {
        return $this->_DateM;
    }
    public function HeureDebutM()
    {
        return $this->_HeureDebutM;
    }
    public function rangTournoi()
    {
        return $this->_rangTournoi;
    }
  
}