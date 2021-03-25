<?php

namespace App\Entity;

use App\Repository\DemandeCertificationRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
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
     * @Assert\NotBlank(message="NomParticipant is required")
     */
    private $NomParticipant;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="PrenomPart is required")
     */
    private $PrenomParticipant;

    /**
     * @ORM\Column(type="date")
     * @Assert\NotBlank(message="Date is required")
     */
    private $DateDemande;

    /**
     * @ORM\Column(type="text", length=255)
     * @Assert\NotBlank(message="Experience is required")
     *  * @Assert\Length(
     *      min = 10,    
     *      minMessage = "Experience must be at least {{ limit }} characters long",
     * )
     */
    private $ExperienceParticipant;

    /**
     * @ORM\ManyToOne(targetEntity=Certification::class, inversedBy="yes")
     * @ORM\JoinColumn(nullable=false)
     * @Assert\NotBlank(message="Certification is required")
     */
    private $Certification;

    /**
     * @ORM\Column(type="string", length=30)
     * @Assert\NotBlank(message="Email is required")
     * @Assert\Email(
     *     message = "The email '{{ value }}' is not a valid email.")
     */
    private $Email;

   
    protected $captchaCode;
    
    public function getCaptchaCode()
    {
      return $this->captchaCode;
    }

    public function setCaptchaCode($captchaCode)
    {
      $this->captchaCode = $captchaCode;
    }



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

    public function getCertification(): ?Certification
    {
        return $this->Certification;
    }

    public function setCertification(?Certification $Certification): self
    {
        $this->Certification = $Certification;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->Email;
    }

    public function setEmail(string $Email): self
    {
        $this->Email = $Email;

        return $this;
    }

    
}
