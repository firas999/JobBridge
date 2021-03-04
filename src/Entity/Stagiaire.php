<?php

namespace App\Entity;

use App\Repository\StagiaireRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=StagiaireRepository::class)
 */
class Stagiaire
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
    private $Etablissement;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $TypeDiplome;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $filiereDiplome;

    /**
     * @ORM\Column(type="date")
     */
    private $DateDiplome;

    /**
     * @ORM\Column(type="integer")
     */
    private $Experience;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Competence;

    /**
     * @ORM\OneToOne(targetEntity=CandidatStage::class, mappedBy="IDStagiaire", cascade={"persist", "remove"})
     */
    private $candidatStage;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getEtablissement(): ?string
    {
        return $this->Etablissement;
    }

    public function setEtablissement(string $Etablissement): self
    {
        $this->Etablissement = $Etablissement;

        return $this;
    }

    public function getTypeDiplome(): ?string
    {
        return $this->TypeDiplome;
    }

    public function setTypeDiplome(string $TypeDiplome): self
    {
        $this->TypeDiplome = $TypeDiplome;

        return $this;
    }

    public function getFiliereDiplome(): ?string
    {
        return $this->filiereDiplome;
    }

    public function setFiliereDiplome(string $filiereDiplome): self
    {
        $this->filiereDiplome = $filiereDiplome;

        return $this;
    }

    public function getDateDiplome(): ?\DateTimeInterface
    {
        return $this->DateDiplome;
    }

    public function setDateDiplome(\DateTimeInterface $DateDiplome): self
    {
        $this->DateDiplome = $DateDiplome;

        return $this;
    }

    public function getExperience(): ?int
    {
        return $this->Experience;
    }

    public function setExperience(int $Experience): self
    {
        $this->Experience = $Experience;

        return $this;
    }

    public function getCompetence(): ?string
    {
        return $this->Competence;
    }

    public function setCompetence(string $Competence): self
    {
        $this->Competence = $Competence;

        return $this;
    }

    public function getCandidatStage(): ?CandidatStage
    {
        return $this->candidatStage;
    }

    public function setCandidatStage(CandidatStage $candidatStage): self
    {
        // set the owning side of the relation if necessary
        if ($candidatStage->getIDStagiaire() !== $this) {
            $candidatStage->setIDStagiaire($this);
        }

        $this->candidatStage = $candidatStage;

        return $this;
    }
}
