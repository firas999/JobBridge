<?php

namespace App\Entity;

use App\Repository\DemandeCertificationRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=DemandeCertificationRepository::class)
 */
class DemandeCertification
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
    private $NomParticipant;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $PrenomParticipant;

    /**
     * @ORM\Column(type="date")
     */
    private $DateDemande;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $ExperienceParticipant;

    /**
     * @ORM\OneToOne(targetEntity=Certification::class, inversedBy="demandeCertification", cascade={"persist", "remove"})
     * @ORM\JoinColumn(nullable=false)
     */
    private $IdCertif;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdCertification(): ?int
    {
        return $this->IdCertification;
    }

    public function setIdCertification(int $IdCertification): self
    {
        $this->IdCertification = $IdCertification;

        return $this;
    }

    public function getNomParticipant(): ?string
    {
        return $this->NomParticipant;
    }

    public function setNomParticipant(string $NomParticipant): self
    {
        $this->NomParticipant = $NomParticipant;

        return $this;
    }

    public function getPrenomParticipant(): ?string
    {
        return $this->PrenomParticipant;
    }

    public function setPrenomParticipant(string $PrenomParticipant): self
    {
        $this->PrenomParticipant = $PrenomParticipant;

        return $this;
    }

    public function getDateDemande(): ?\DateTimeInterface
    {
        return $this->DateDemande;
    }

    public function setDateDemande(\DateTimeInterface $DateDemande): self
    {
        $this->DateDemande = $DateDemande;

        return $this;
    }

    public function getExperienceParticipant(): ?string
    {
        return $this->ExperienceParticipant;
    }

    public function setExperienceParticipant(string $ExperienceParticipant): self
    {
        $this->ExperienceParticipant = $ExperienceParticipant;

        return $this;
    }

    public function getIdCertif(): ?Certification
    {
        return $this->IdCertif;
    }

    public function setIdCertif(Certification $IdCertif): self
    {
        $this->IdCertif = $IdCertif;

        return $this;
    }
}
