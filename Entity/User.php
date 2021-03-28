<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;


/**
 * @ORM\Entity(repositoryClass=UserRepository::class)
 * @ORM\InheritanceType("SINGLE_TABLE")
 * @ORM\DiscriminatorColumn(name="discr", type="string")
 * @ORM\DiscriminatorMap({"employee" = "Employee","candidat"="Candidat"})
 */

 abstract class User implements UserInterface
         {
             /**
              * @ORM\Id
              * @ORM\GeneratedValue
              * @ORM\Column(type="integer")
              * @Groups({"employee","candidat"})

              */
             private $id;
         
             /**
              * @ORM\Column(type="string", length=255)
              *  @Assert\NotBlank(message="Ce champs est obligatoire")
              * @Groups({"employee","candidat"})

              */
             private $nom;
         
             /**
              * @ORM\Column(type="string", length=255)
               * @Assert\NotBlank(message="Ce champs est obligatoire")
              * @Groups({"employee","candidat"})

              */
             private $prenom;
         
             /**
              * @ORM\Column(type="string", length=255)
              * @Assert\NotBlank(message="Ce champs est obligatoire")
              * @Groups({"read","employee","candidat"})

              */
             private $motdepasse;

     /**
      * @ORM\Column(type="json")

      */
     private $roles = [];



     /**
              * @ORM\Column(type="string", length=255)
      *  @Groups({"employee","candidat"})
              */
             private $img;
         
             public function getId(): ?int
             {
                 return $this->id;
             }
         
             public function getNom(): ?string
             {
                 return $this->nom;
             }
         
             public function setNom(string $nom): self
             {
                 $this->nom = $nom;
         
                 return $this;
             }
         
             public function getPrenom(): ?string
             {
                 return $this->prenom;
             }
         
             public function setPrenom(string $prenom): self
             {
                 $this->prenom = $prenom;
         
                 return $this;
             }
         
             public function getMotdepasse(): ?string
             {
                 return $this->motdepasse;
             }
         
             public function setMotdepasse(string $motdepasse): self
             {
                 $this->motdepasse = $motdepasse;
         
                 return $this;
             }
         

             public function getImg(): ?string
             {
                 return $this->img;
             }

             public function setImg(string $img): self
             {
                 $this->img = $img;

                 return $this;
             }
     public function eraseCredentials()
     {
         // TODO: Implement eraseCredentials() method.
     }
     public function getSalt()
     {
         // TODO: Implement getSalt() method.
     }
     public function getUsername(): ?string
     {
         return $this->getNom();
     }


     public function getRoles(): array
     {
         $roles = $this->roles;
         // guarantee every user at least has ROLE_USER
         $roles[] = 'ROLE_USER';
         return array_unique($this->roles);
     }

     /**
      * @param string $role
      * @return $this
      */
     public function addRole(string $role): self
     {
         array_push($this->roles,$role);
         return $this;
     }

     /**
      * @param array<mixed> $roles
      * @return $this
      */
     public function setRoles(array $roles): self
     {
         $this->roles = $roles;
         return $this;
     }
         }
