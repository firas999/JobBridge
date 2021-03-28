<?php

namespace App\Entity;

use App\Repository\Rec2Repository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=Rec2Repository::class)
 */
class Rec2
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="text")
     */
    private $texte;

    /**
     * @ORM\Column(type="date")
     */
    private $date;

    /**
     * @ORM\ManyToOne(targetEntity=Employee::class, inversedBy="rec2s")
     */
    private $employee;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTexte(): ?string
    {
        return $this->texte;
    }

    public function setTexte(string $texte): self
    {
        $this->texte = $texte;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getEmployee(): ?Employee
    {
        return $this->employee;
    }


    public function setEmployee(?Employee $employee): self
    {
        $this->employee = $employee;

        return $this;
    }


        public function __toString() {
        return (string) $this->employee;
    }
        // TODO: Implement __toString() method.

}
