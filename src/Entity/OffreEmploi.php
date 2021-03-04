<?php

namespace App\Entity;

use App\Repository\OffreEmploiRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=OffreEmploiRepository::class)
 */
class OffreEmploi
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Type;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Experience;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Exigence;

    /**
     * @ORM\Column(type="integer")
     */
    private $IdSociete;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Poste;

    /**
     * @ORM\Column(type="integer")
     */
    private $Salaire;

    /**
     * @ORM\ManyToOne(targetEntity=Entreprise::class, inversedBy="OffresEmploi")
     * @ORM\JoinColumn(nullable=false)
     */
    private $entreprise;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getType(): ?string
    {
        return $this->Type;
    }

    public function setType(string $Type): self
    {
        $this->Type = $Type;

        return $this;
    }

    public function getExperience(): ?string
    {
        return $this->Experience;
    }

    public function setExperience(string $Experience): self
    {
        $this->Experience = $Experience;

        return $this;
    }

    public function getExigence(): ?string
    {
        return $this->Exigence;
    }

    public function setExigence(string $Exigence): self
    {
        $this->Exigence = $Exigence;

        return $this;
    }

    public function getIdSociete(): ?int
    {
        return $this->IdSociete;
    }

    public function setIdSociete(int $IdSociete): self
    {
        $this->IdSociete = $IdSociete;

        return $this;
    }

    public function getPoste(): ?string
    {
        return $this->Poste;
    }

    public function setPoste(string $Poste): self
    {
        $this->Poste = $Poste;

        return $this;
    }

    public function getSalaire(): ?int
    {
        return $this->Salaire;
    }

    public function setSalaire(int $Salaire): self
    {
        $this->Salaire = $Salaire;

        return $this;
    }

    public function getEntreprise(): ?Entreprise
    {
        return $this->entreprise;
    }

    public function setEntreprise(?Entreprise $entreprise): self
    {
        $this->entreprise = $entreprise;

        return $this;
    }
}
