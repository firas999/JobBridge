<?php

namespace App\Entity;

use App\Repository\EmployeRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=EmployeRepository::class)
 */
class Employe
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
    private $Diplome;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Entreprise;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $TypeContrat;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $PosteOccupe;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Competence;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Exerience;

    /**
     * @ORM\OneToOne(targetEntity=CandidatEmploi::class, mappedBy="IdEmploye", cascade={"persist", "remove"})
     */
    private $candidatEmploi;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDiplome(): ?string
    {
        return $this->Diplome;
    }

    public function setDiplome(string $Diplome): self
    {
        $this->Diplome = $Diplome;

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

    public function getTypeContrat(): ?string
    {
        return $this->TypeContrat;
    }

    public function setTypeContrat(string $TypeContrat): self
    {
        $this->TypeContrat = $TypeContrat;

        return $this;
    }

    public function getPosteOccupe(): ?string
    {
        return $this->PosteOccupe;
    }

    public function setPosteOccupe(string $PosteOccupe): self
    {
        $this->PosteOccupe = $PosteOccupe;

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

    public function getExerience(): ?string
    {
        return $this->Exerience;
    }

    public function setExerience(string $Exerience): self
    {
        $this->Exerience = $Exerience;

        return $this;
    }

    public function getCandidatEmploi(): ?CandidatEmploi
    {
        return $this->candidatEmploi;
    }

    public function setCandidatEmploi(?CandidatEmploi $candidatEmploi): self
    {
        // unset the owning side of the relation if necessary
        if ($candidatEmploi === null && $this->candidatEmploi !== null) {
            $this->candidatEmploi->setIdEmploye(null);
        }

        // set the owning side of the relation if necessary
        if ($candidatEmploi !== null && $candidatEmploi->getIdEmploye() !== $this) {
            $candidatEmploi->setIdEmploye($this);
        }

        $this->candidatEmploi = $candidatEmploi;

        return $this;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(?string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }
}
