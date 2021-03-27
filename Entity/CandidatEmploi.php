<?php

namespace App\Entity;

use App\Repository\CandidatEmploiRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CandidatEmploiRepository::class)
 */
class CandidatEmploi
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
    private $DateCandiature;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $email;

    /**
     * @ORM\OneToOne(targetEntity=Employe::class, inversedBy="candidatEmploi", cascade={"persist", "remove"})
     */
    private $IdEmploye;

    /**
     * @ORM\OneToOne(targetEntity=Entreprise::class, inversedBy="candidatEmploi", cascade={"persist", "remove"})
     * @ORM\JoinColumn(nullable=false)
     */
    private $IDEntreprise;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdemploye(): ?int
    {
        return $this->Idemploye;
    }

    public function setIdemploye(int $Idemploye): self
    {
        $this->Idemploye = $Idemploye;

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

    public function getDateCandiature(): ?\DateTimeInterface
    {
        return $this->DateCandiature;
    }

    public function setDateCandiature(\DateTimeInterface $DateCandiature): self
    {
        $this->DateCandiature = $DateCandiature;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }
}
