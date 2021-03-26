<?php

namespace App\Entity;

use App\Repository\CertificationRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CertificationRepository::class)
 */
class Certification
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
     * @ORM\Column(type="date")
     */
    private $DatePassage;

    /**
     * @ORM\Column(type="integer")
     */
    private $Prix;

    /**
     * @ORM\Column(type="text")
     */
    private $Description;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Nom;

  

    /**
     * @ORM\ManyToOne(targetEntity=Entreprise::class, inversedBy="certifications")
     * @ORM\JoinColumn(nullable=false)
     */
    private $IdEntreprise;

    /**
     * @ORM\OneToOne(targetEntity=DemandeCertification::class, mappedBy="IdCertif", cascade={"persist", "remove"})
     */
    private $demandeCertification;

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

    public function getDatePassage(): ?\DateTimeInterface
    {
        return $this->DatePassage;
    }

    public function setDatePassage(\DateTimeInterface $DatePassage): self
    {
        $this->DatePassage = $DatePassage;

        return $this;
    }

    public function getPrix(): ?int
    {
        return $this->Prix;
    }

    public function setPrix(int $Prix): self
    {
        $this->Prix = $Prix;

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

    public function getNom(): ?string
    {
        return $this->Nom;
    }

    public function setNom(string $Nom): self
    {
        $this->Nom = $Nom;

        return $this;
    }

    public function getIdSociete(): ?int
    {
        return $this->idSociete;
    }

    public function setIdSociete(int $idSociete): self
    {
        $this->idSociete = $idSociete;

        return $this;
    }

    public function getIdEntreprise(): ?Entreprise
    {
        return $this->IdEntreprise;
    }

    public function setIdEntreprise(?Entreprise $IdEntreprise): self
    {
        $this->IdEntreprise = $IdEntreprise;

        return $this;
    }

    public function getDemandeCertification(): ?DemandeCertification
    {
        return $this->demandeCertification;
    }

    public function setDemandeCertification(DemandeCertification $demandeCertification): self
    {
        // set the owning side of the relation if necessary
        if ($demandeCertification->getIdCertif() !== $this) {
            $demandeCertification->setIdCertif($this);
        }

        $this->demandeCertification = $demandeCertification;

        return $this;
    }
}
