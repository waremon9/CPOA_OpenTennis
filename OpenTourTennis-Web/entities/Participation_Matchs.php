<?php
class Matchs{

    private $_idJ;
    private $_idM;

    public function __construct($idJ, $idM)
    {
        $this->_idJ = $idJ;
        $this->_idM = $idM;
    }

    public function idJ()
    {
        return $this->_idJ;
    }
    public function idM()
    {
        return $this->_idM;
    }
  
}