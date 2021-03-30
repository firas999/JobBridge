<?php

namespace App\Entity;

use App\Repository\EvenementRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=EvenementRepository::class)
 */
class Evenement
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $Nom;

    /**
     * @ORM\Column(type="string", length=50)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $Entreprise;

    /**
     * @ORM\Column(type="text")
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $Description;

    /**
     * @ORM\Column(type="date")
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $Date;

    /**
     * @ORM\Column(type="float", nullable=true)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     * @Assert\Positive(message="Prix doit etre un nombre positive")
     */
    private $Prix;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $Adresse;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $Cap;

    /**
     * @ORM\Column(type="time", nullable=true)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $Horaire;




    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->Nom;
    }

    public function setNom(string $Nom): self
    {
        $this->Nom = $Nom;

        return $this;
    }

    public function getEntreprise(): ?string
    {
        return $this->Entreprise;
    }

    public function setEntreprise(string $Entreprise): self
    {
        $this->Entreprise = $Entreprise;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->Description;
    }

    public function setDescription(string $Description): self
    {
        $this->Description = $Description;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->Date;
    }

    public function setDate(\DateTimeInterface $Date): self
    {
        $this->Date = $Date;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->Prix;
    }

    public function setPrix(?float $Prix): self
    {
        $this->Prix = $Prix;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->Adresse;
    }

    public function setAdresse(?string $Adresse): self
    {
        $this->Adresse = $Adresse;

        return $this;
    }

    public function getCap(): ?int
    {
        return $this->Cap;
    }

    public function setCap(?int $Cap): self
    {
        $this->Cap = $Cap;

        return $this;
    }

    public function getHoraire(): ?\DateTimeInterface
    {
        return $this->Horaire;
    }

    public function setHoraire(?\DateTimeInterface $Horaire): self
    {
        $this->Horaire = $Horaire;

        return $this;
    }



}
