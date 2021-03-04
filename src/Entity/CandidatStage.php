<?php

namespace App\Entity;

use App\Repository\CandidatStageRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CandidatStageRepository::class)
 */
class CandidatStage
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;


    /**
     * @ORM\Column(type="date")
     */
    private $DateCandidature;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $TypeStage;

    /**
     * @ORM\OneToOne(targetEntity=Entreprise::class, inversedBy="candidatStage", cascade={"persist", "remove"})
     * @ORM\JoinColumn(nullable=false)
     */
    private $IDEntreprise;

    /**
     * @ORM\OneToOne(targetEntity=Stagiaire::class, inversedBy="candidatStage", cascade={"persist", "remove"})
     * @ORM\JoinColumn(nullable=false)
     */
    private $IDStagiaire;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdStagiaire(): ?int
    {
        return $this->IdStagiaire;
    }

    public function setIdStagiaire(int $IdStagiaire): self
    {
        $this->IdStagiaire = $IdStagiaire;

        return $this;
    }

    public function getIdEntreprise(): ?int
    {
        return $this->IdEntreprise;
    }

    public function setIdEntreprise(int $IdEntreprise): self
    {
        $this->IdEntreprise = $IdEntreprise;

        return $this;
    }

    public function getDateCandidature(): ?\DateTimeInterface
    {
        return $this->DateCandidature;
    }

    public function setDateCandidature(\DateTimeInterface $DateCandidature): self
    {
        $this->DateCandidature = $DateCandidature;

        return $this;
    }

    public function getTypeStage(): ?string
    {
        return $this->TypeStage;
    }

    public function setTypeStage(string $TypeStage): self
    {
        $this->TypeStage = $TypeStage;

        return $this;
    }
}
