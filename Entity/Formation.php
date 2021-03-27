<?php

namespace App\Entity;

use App\Repository\FormationRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=FormationRepository::class)
 */
class Formation
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
    private $Description;

    /**
     * @ORM\Column(type="integer")
     */
    private $VolumeHoraire;

    /**
     * @ORM\Column(type="date")
     */
    private $DateFormation;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Adresse;

    /**
     * @ORM\ManyToOne(targetEntity=Entreprise::class, inversedBy="Formations")
     * @ORM\JoinColumn(nullable=false)
     */
    private $entreprise;

    public function getId(): ?int
    {
        return $this->id;
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

    public function getVolumeHoraire(): ?int
    {
        return $this->VolumeHoraire;
    }

    public function setVolumeHoraire(int $VolumeHoraire): self
    {
        $this->VolumeHoraire = $VolumeHoraire;

        return $this;
    }

    public function getDateFormation(): ?\DateTimeInterface
    {
        return $this->DateFormation;
    }

    public function setDateFormation(\DateTimeInterface $DateFormation): self
    {
        $this->DateFormation = $DateFormation;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->Adresse;
    }

    public function setAdresse(string $Adresse): self
    {
        $this->Adresse = $Adresse;

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
